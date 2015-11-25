package com.epam.preprod.tymchenko.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.util.ClassNameUtil;

public class NoCacheFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	public void init(FilterConfig filterConfig) {
		LOG.info("NoCacheFilter.init(FilterConfig filterConfig)");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.info("NoCacheFilter.doFilter(ServletRequest request, ServletResponse response, FilterChain chain)");
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "No-cache");
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		LOG.info("NoCacheFilter.destroy()");

	}
}