package com.company.util.locale;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.constant.Constants;
import com.company.tymchenko.util.ClassNameUtil;
import com.company.util.locale.handler.LocaleHandler;

public class CookieLocaleHandler extends LocaleHandler{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private Cookie[] cookies;

	@Override
	protected Locale find(HttpServletRequest request) {
		LOG.info("CookieLocaleFinder.find(HttpServletRequest request)");

		Locale locale = getFromParameters(request);
		
		if(locale == null){
			cookies = request.getCookies();
			
			if(isContainsLocale()){
				return getLocale();
			}
			
			return next.find(request);
		}
		
		return locale;
	}

	@Override
	protected Locale getLocale() {
		LOG.info("CookieLocaleFinder.getLocale()");
		
		Locale cookieLocale = null;
		
		for(Cookie cookie: cookies){
            if (Constants.LOCALE.equalsIgnoreCase(cookie.getName())) {
            	
            	cookieLocale = new Locale(cookie.getValue());
            	break;
            }
		}
		
		return cookieLocale;
	}

	@Override
	protected boolean isContainsLocale() {
		LOG.info("CookieLocaleFinder.isContainsLocale()");
		
		if(cookies != null){
			for(Cookie cookie: cookies){
	            if (Constants.LOCALE.equalsIgnoreCase(cookie.getName())) {
	            	return true;
	            }
			}
		}
		
		return false;
	}

	@Override
	public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		LOG.info("CookieLocaleFinder.saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale)");
		
		Cookie cookie = new Cookie(Constants.WEB_LOCALE, locale.toString());
		cookie.setMaxAge(cookieTimeout);
		
		response.addCookie(cookie);
	}
}
