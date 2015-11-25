package com.epam.preprod.tymchenko.util.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public abstract class LocaleFinder {
	
    protected LocaleFinder next;

    public LocaleFinder setNext(LocaleFinder finder) {
        next = finder;
        return finder;
    }
    
    protected abstract Locale find(HttpServletRequest request);
    
    protected abstract Locale getLocale();
    
    protected abstract boolean isContainsLocale();

}
