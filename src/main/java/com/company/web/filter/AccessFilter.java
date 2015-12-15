package com.company.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.company.constant.AppScopeConstants;
import com.company.constant.Constants;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.util.ClassNameUtil;
import com.company.util.FilterUtil;
import com.company.util.ViewPath;
import com.company.web.filter.access.SecurityDispatcher;

public class AccessFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	private SecurityDispatcher securityDispatcher;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("AccessFilter.init(FilterConfig filterConfig)");

		securityDispatcher = (SecurityDispatcher) filterConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_SECURITY_DISPATCHER);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.trace("AccessFilter.doFilter(ServletRequest request, ServletResponse response, FilterChain chain)");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		int id;
		User user;
		String uri;
		String roleName;
		HttpSession session;

		uri = String.valueOf(httpServletRequest.getRequestURI());

		if (FilterUtil.isStaticElement(httpServletRequest.getServletPath()) || !securityDispatcher.isUrlSecured(uri)) {
			chain.doFilter(request, response);
			return;
		}

		session = httpServletRequest.getSession();
		user = (User) session.getAttribute(Constants.WEB_USER);

		if (user == null) {
			request.getRequestDispatcher(ViewPath.LOGIN_PAGE).forward(request, response);
			return;
		}

		id = (int) user.getRoleId();
		roleName = Role.getNameByKey(id);

		if (securityDispatcher.isAccessAllowed(uri, roleName)) {
			chain.doFilter(request, response);
			return;
		}

		httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
	}

	@Override
	public void destroy() {
		LOG.info("AccessFilter.destroy()");
	}

}
