package com.epam.preprod.tymchenko.util.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.util.locale.handler.LocaleHandler;

public class SessionLocaleHandler extends LocaleHandler{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private HttpSession session;

	@Override
	protected Locale find(HttpServletRequest request) {
		LOG.info("SessionLocaleFinder.find(HttpServletRequest request)");
		
		Locale locale = getFromParameters(request);
		
		if(locale == null){
			session = request.getSession(false);
			
			if(isContainsLocale()){
				return getLocale();
			}
			
			return next.find(request);
		}
		
		return locale;
	}

	@Override
	protected Locale getLocale() {
		LOG.info("SessionLocaleFinder.getLocale()");
		
		return new Locale( (String) session.getAttribute(Constants.LOCALE));
	}

	@Override
	protected boolean isContainsLocale() {
		LOG.info("SessionLocaleFinder.isContainsLocale()");
		
		return session != null && session.getAttribute(Constants.LOCALE) != null;
	}

	@Override
	public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		LOG.info("SessionLocaleFinder.saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale)");
		
		session.setAttribute(Constants.WEB_LOCALE, locale.toString());
	}

}
