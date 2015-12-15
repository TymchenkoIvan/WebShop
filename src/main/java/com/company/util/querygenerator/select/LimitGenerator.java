package com.company.util.querygenerator.select;

import org.apache.log4j.Logger;

import com.company.service.bean.SortFormBean;
import com.company.tymchenko.util.ClassNameUtil;

public class LimitGenerator extends Generator{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private int page;
	private int onPage;
	
	public LimitGenerator(int page, int onPage){
		this.page = page;
		this.onPage = onPage;
		
		LOG.info("Page: "+ page +" On page: "+onPage);
	}

	@Override
	protected StringBuilder append(SortFormBean bean, StringBuilder string) {
		
		int count = setCount();
		int from = setFrom();
		
		string.append(" LIMIT " + from + ", " + count);
		
		return next.append(bean, string);
	}
	
	private int setCount(){
		return onPage;
	}
	
	private int setFrom(){
		return ((page - 1) * onPage);
	}
}
