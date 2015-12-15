package com.company.util.locale;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.company.tymchenko.util.ClassNameUtil;

public class ChainLocaleFinder{	
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private LocaleFinder choosenFinder;
	private ClientLocaleFinder clients;
	private DefaultLocaleFinder def;
	
	public ChainLocaleFinder(LocaleFinder choosenFinder, List<String> appLocales){
		LOG.info("ChainLocaleFinder(List<String> appLocales)");
		
		this.choosenFinder = choosenFinder;
		clients = new ClientLocaleFinder(appLocales);
		def = new DefaultLocaleFinder();
		
		choosenFinder.setNext(clients);
		clients.setNext(def);
	} 

	public Locale find(HttpServletRequest request) {
		LOG.info("ChainLocaleFinder.find(HttpServletRequest request)");
		
		return choosenFinder.find(request);
	}
}
