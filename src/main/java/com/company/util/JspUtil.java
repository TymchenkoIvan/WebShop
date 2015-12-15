package com.company.util;

import java.util.List;

import org.apache.log4j.Logger;

public class JspUtil {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private static final String PAGE_REGEX = "[?,&]page=\\d*";
	private static final String PAGE_PARAMETER = "page=";
	private static final String PAGE_PRODUCTS = "/products?";
	
	private static final String LANG_REGEX = "[?,&]lang=\\w*";
	private static final String LANG_PARAMETER = "lang=";
	
	@SuppressWarnings("rawtypes")
	public static boolean contains(List list, Object o) {
		LOG.info("contains(List list, Object o)");
		
		boolean res = false;
		
		if(list == null || list.size() == 0){
			res = false;
		} else if(list.contains(o.toString())){
			res = true;
		}
		
		return res;
	}
	
	public static String changePage(String url, Integer page) {
		LOG.info("changePage(String url, int page)");
		
		url = url.replaceAll(PAGE_REGEX, "");
		
		if(url.contains(PAGE_PRODUCTS)){
			url = url + "&" + PAGE_PARAMETER + page;
		} else{
			url = url + "?" +  PAGE_PARAMETER + page;
		}
		
		return url;
	}
	
	public static String changeLang(String url, String lang) {
		LOG.info("changeLang(String url, String lang)");
		
		url = url.replaceAll(LANG_REGEX, "");
		
		if(url.contains("?")){
			url = url + "&" + LANG_PARAMETER + lang;
		} else{
			url = url + "?" +  LANG_PARAMETER + lang;
		}
		
		return url;
	}
}
