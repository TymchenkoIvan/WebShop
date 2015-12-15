package com.company.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.constant.AppScopeConstants;
import com.company.constant.Constants;
import com.company.constant.DeployConstants;
import com.company.tymchenko.util.ClassNameUtil;
import com.company.tymchenko.util.FilterUtil;
import com.company.util.locale.ChainLocaleFinder;
import com.company.util.locale.handler.LocaleHandler;
import com.company.web.filter.locale.LocaleRequestWrapper;

public class LocaleFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	private List<String> appLocales;
	private LocaleHandler localeHandler;
	private int cookieTimeout;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("LocaleFilter.init(FilterConfig filterConfig)");

		appLocales = paramsAsList(filterConfig.getInitParameter(DeployConstants.WEB_XML_LOCALES));
		localeHandler = (LocaleHandler) filterConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_LOCALE_HANDLER);
		localeHandler.setAppLocales(appLocales);
		localeHandler.setCookieTimeout(cookieTimeout);

		LOG.info("appLocales: " + appLocales);
		LOG.info("localeHandler: " + localeHandler.getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.info("LocaleFilter.doFilter(ServletRequest request, ServletResponse response, FilterChain chain)");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		ChainLocaleFinder chainFinder = new ChainLocaleFinder(localeHandler, appLocales);

		Locale locale = chainFinder.find(httpRequest);
		String oldUrl = getUrl(httpRequest);

		localeHandler.saveLocale(httpRequest, httpResponse, locale);

		HttpServletRequest localeRequestWrapper = new LocaleRequestWrapper(locale, httpRequest);
		localeRequestWrapper.setAttribute(Constants.WEB_OLD_URL, oldUrl);

		if (!FilterUtil.isStaticElement(oldUrl)) {
			LOG.debug("Setted old url: " + oldUrl);
		}
		LOG.debug("Setted locale: " + locale.toString());

		chain.doFilter(localeRequestWrapper, response);
	}

	@Override
	public void destroy() {
		LOG.info("LocaleFilter.destroy()");
	}

	private List<String> paramsAsList(String line) {
		LOG.info("LocaleFilter.paramsAsList(String line)");

		List<String> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(line);

		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}

		return list;
	}

	private String getUrl(HttpServletRequest request) {
		LOG.info("LocaleFilter.getUrl(HttpServletRequest request)");

		StringBuffer requestURL = request.getRequestURL();

		if (request.getQueryString() != null) {
			requestURL.append("?").append(request.getQueryString());
		}

		return requestURL.toString();
	}
}
