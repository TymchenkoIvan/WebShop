package com.epam.preprod.tymchenko.service.bean.factory;

import javax.servlet.http.HttpServletRequest;

import com.epam.preprod.tymchenko.service.bean.Bean;

public abstract class RequestBeanCreator {
	
	public abstract Bean create(HttpServletRequest request);

}
