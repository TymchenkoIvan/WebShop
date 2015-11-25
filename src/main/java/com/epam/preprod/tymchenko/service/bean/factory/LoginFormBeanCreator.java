package com.epam.preprod.tymchenko.service.bean.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.service.bean.Bean;
import com.epam.preprod.tymchenko.service.bean.LoginFormBean;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.Constants;

public class LoginFormBeanCreator extends RequestBeanCreator{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@Override
	public Bean create(HttpServletRequest request) {
		LOG.info("LoginFormBeanCreator.create(HttpServletRequest request)");

		LoginFormBean bean = new LoginFormBean();

		bean.setLogin(request.getParameter(Constants.FORM_LOGIN));
		bean.setPassword(request.getParameter(Constants.FORM_PASSWORD));

		return bean;
	}
}
