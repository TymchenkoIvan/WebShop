package com.epam.preprod.tymchenko.util.locale.handler;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.util.locale.LocaleFinder;
import com.epam.preprod.tymchenko.util.locale.LocaleSaver;

public abstract class LocaleHandler extends LocaleFinder implements LocaleSaver{
	
	private List<String> appLocales;
	protected int cookieTimeout;

	public void setAppLocales(List<String> appLocales) {
		this.appLocales = appLocales;
	}

	public void setCookieTimeout(int cookieTimeout) {
		this.cookieTimeout = cookieTimeout;
	}

	public Locale getFromParameters(HttpServletRequest request) {
		String localeLine = request.getParameter(Constants.WEB_LOCALE);
		Locale locale = null;

		if(localeLine != null){
			for(String s: appLocales){
				if(localeLine.equalsIgnoreCase(s)){
					locale = new Locale(localeLine);
					break;
				}
			}
		}
		
		return locale;
	}

}
