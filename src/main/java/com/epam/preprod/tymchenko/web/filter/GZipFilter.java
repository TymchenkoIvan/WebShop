package com.epam.preprod.tymchenko.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.web.filter.gzip.GZipResponseWrapper;

public class GZipFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	public static final String ACCEPT_ENCODING = "accept-encoding";
	public static final String GZIP = "gzip";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.trace("GZipFilter.init(FilterConfig filterConfig)");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		LOG.trace("GZipFilter.doFilter(ServletRequest request, ServletResponse response, FilterChain chain)");

		if (request instanceof HttpServletRequest) {

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;

			String acceptEncoding = httpRequest.getHeader(ACCEPT_ENCODING);

			if (acceptEncoding != null && acceptEncoding.contains(GZIP)) {
				GZipResponseWrapper gzipResponse = new GZipResponseWrapper(httpResponse);
				chain.doFilter(httpRequest, gzipResponse);
				
				gzipResponse.finishResponse();
				return;
			}
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		LOG.trace("GZipFilter.destroy()");
	}
}