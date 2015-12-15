package com.company.service.bean.factory;

import javax.servlet.http.HttpServletRequest;

import com.company.service.bean.Bean;

public abstract class RequestBeanCreator {
	
	public abstract Bean create(HttpServletRequest request);

}
