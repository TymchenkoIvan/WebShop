package com.epam.preprod.tymchenko.util.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocaleSaver {
	
	void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);

}
