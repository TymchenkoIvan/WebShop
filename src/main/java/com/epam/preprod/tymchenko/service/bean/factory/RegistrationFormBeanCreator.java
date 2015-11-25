package com.epam.preprod.tymchenko.service.bean.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.service.bean.Bean;
import com.epam.preprod.tymchenko.service.bean.RegistrationFormBean;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.Constants;

public class RegistrationFormBeanCreator extends RequestBeanCreator{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@Override
	public Bean create(HttpServletRequest request) {
		LOG.info("RegistrationFormBeanCreator.create(HttpServletRequest request)");

		RegistrationFormBean bean = new RegistrationFormBean();

		bean.setFirstName(request.getParameter(Constants.FORM_FIRST_NAME));
		bean.setLastName(request.getParameter(Constants.FORM_LAST_NAME));
		bean.setLogin(request.getParameter(Constants.FORM_LOGIN));
		bean.setMail(request.getParameter(Constants.FORM_MAIL));
		bean.setPassword(request.getParameter(Constants.FORM_PASSWORD));
		bean.setRePassword(request.getParameter(Constants.FORM_RE_PASSWORD));
		bean.setCaptchaCode(request.getParameter(Constants.FORM_CAPTCHA_CODE));
		bean.setNotifications(request.getParameterValues(Constants.FORM_NOTIFICATION));

		return bean;
	}
}
