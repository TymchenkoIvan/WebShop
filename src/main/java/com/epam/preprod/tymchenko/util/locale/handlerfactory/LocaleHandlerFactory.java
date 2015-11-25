package com.epam.preprod.tymchenko.util.locale.handlerfactory;

import java.util.EnumMap;

import com.epam.preprod.tymchenko.util.locale.CookieLocaleHandler;
import com.epam.preprod.tymchenko.util.locale.SessionLocaleHandler;
import com.epam.preprod.tymchenko.util.locale.handler.LocaleHandler;

public class LocaleHandlerFactory {
	
	@SuppressWarnings("serial")
	public static final EnumMap<LocaleHandlerType, LocaleHandler> HANDLERS = new EnumMap<LocaleHandlerType, LocaleHandler>(LocaleHandlerType.class) {{
	    put(LocaleHandlerType.SESSION, new SessionLocaleHandler());
	    put(LocaleHandlerType.COOKIE, new CookieLocaleHandler());
	}};
}
