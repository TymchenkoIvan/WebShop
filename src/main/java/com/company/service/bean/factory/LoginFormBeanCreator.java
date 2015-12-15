package com.company.service.bean.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.company.constant.Constants;
import com.company.service.bean.Bean;
import com.company.service.bean.LoginFormBean;
import com.company.util.ClassNameUtil;

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
