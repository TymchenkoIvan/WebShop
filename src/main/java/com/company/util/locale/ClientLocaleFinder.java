package com.company.util.locale;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.company.tymchenko.util.ClassNameUtil;

public class ClientLocaleFinder extends LocaleFinder {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	private List<String> appLocales;
	
	private Enumeration<Locale> clientLocales;
	private Locale matchesLocale;

	protected ClientLocaleFinder(List<String> appLocales) {
		LOG.trace("ClientLocaleFinder(List<String> appLocales)");

		this.appLocales = appLocales;
	}

	@Override
	protected Locale find(HttpServletRequest request) {
		LOG.trace("ClientLocaleFinder.find(HttpServletRequest request)");

		clientLocales = request.getLocales();

		if (isContainsLocale()) {
			return getLocale();
		}
		return next.find(request);
	}

	@Override
	protected Locale getLocale() {
		LOG.trace("ClientLocaleFinder.getLocale()");

		return matchesLocale;
	}

	@Override
	protected boolean isContainsLocale() {
		LOG.trace("ClientLocaleFinder.isContainsLocale()");

		while (clientLocales.hasMoreElements()) {
			String s = clientLocales.nextElement().toString().toLowerCase();
			
			if (appLocales.contains(s)) {
				matchesLocale = new Locale(s);
				return true;
			}
		}

		return false;
	}
}