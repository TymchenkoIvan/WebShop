package com.company.web.filter.locale;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {	
	
    private final Locale locale;	

    public LocaleRequestWrapper(Locale locale, HttpServletRequest request) {	
        super(request);	
        this.locale = locale;	
    }	

    @Override	
    public Locale getLocale() {	
        return locale;	
    }	

    @Override	
    public Enumeration<Locale> getLocales() {	
        return new Enumeration<Locale>() {	
            private boolean next = true;	

            @Override	
            public boolean hasMoreElements() {	
                return next;	
            }	

            @Override	
            public Locale nextElement() {	
                next = false;	
                return locale;	
            }	
        };	
    }	
}

