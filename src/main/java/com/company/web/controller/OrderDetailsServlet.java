package com.company.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.company.constant.Constants;
import com.company.service.bean.OrderDetailsFormBean;
import com.company.service.bean.RequestBeanType;
import com.company.service.bean.factory.RequestBeanFactory;
import com.company.util.ClassNameUtil;
import com.company.util.Validator;
import com.company.util.ViewPath;

@SuppressWarnings("serial")
public class OrderDetailsServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doGet()");
		
		response.sendRedirect(ViewPath.ORDER_PAGE);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doPost()");
		
		HttpSession session;
		OrderDetailsFormBean bean;
		Map<String, String> exceptions = null;
		
		bean = (OrderDetailsFormBean)RequestBeanFactory.createBean(RequestBeanType.ORDER_DETAILS_FORM, request);
		exceptions = Validator.validate(bean);
			
		session = request.getSession();
		session.setAttribute(Constants.WEB_SESSION_ORDER_DETAILS, bean);
			
		doGet(request, response);
			
		if(exceptions.size() > 0){
			LOG.warn("Exceptions :" + exceptions.toString());
			
			request.setAttribute(Constants.ERROR_EXCEPTIONS_MAP, exceptions);
			response.sendRedirect(ViewPath.DETAILS_PAGE);
		}
	}
}