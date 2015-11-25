package com.epam.preprod.tymchenko.util.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.util.ClassNameUtil;

public class DefaultLocaleFinder extends LocaleFinder{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	@Override
	protected Locale find(HttpServletRequest request) {
		LOG.info("DefaultLocaleFinder.find(HttpServletRequest request)");
		
		return getLocale();
	}

	@Override
	protected Locale getLocale() {
		LOG.info("DefaultLocaleFinder.getLocale()");
		
		return Locale.getDefault();
	}

	@Override
	protected boolean isContainsLocale() {
		LOG.info("DefaultLocaleFinder.isContainsLocale()");
		
		throw new UnsupportedOperationException();
	}
}
