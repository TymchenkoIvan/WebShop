package com.epam.preprod.tymchenko.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.entity.User;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.exception.Messages;
import com.epam.preprod.tymchenko.exception.ValidationException;
import com.epam.preprod.tymchenko.service.UserService;
import com.epam.preprod.tymchenko.service.bean.RequestBeanType;
import com.epam.preprod.tymchenko.service.bean.LoginFormBean;
import com.epam.preprod.tymchenko.service.bean.factory.RequestBeanFactory;
import com.epam.preprod.tymchenko.constant.AppScopeConstants;
import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.util.Validator;
import com.epam.preprod.tymchenko.util.ViewPath;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private UserService userService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		LOG.info("LoginServlet.init(ServletConfig servletConfig)");
		
		userService = (UserService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_USER_SERVICE);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("LoginServlet.doGet(HttpServletRequest request, HttpServletResponse response)");
		
		response.sendRedirect(ViewPath.LOGIN_PAGE);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("LoginServlet.doPost(HttpServletRequest request, HttpServletResponse response)");
		
		HttpSession session = request.getSession(true);
		LoginFormBean bean = (LoginFormBean)RequestBeanFactory.createBean(RequestBeanType.LOGIN_FORM, request);
		User user;
		
		try {
			Validator.validate(bean);

			if(userService.verify(bean.getLogin(), bean.getPassword())){
				
				user = userService.getUserByLogin(bean.getLogin());
				session.setAttribute(Constants.WEB_USER, user);
				
				session.removeAttribute(Constants.ERROR_MESSAGE_NAME);
			} else{
				throw new ValidationException(Messages.LOGIN_WRONG);
			}
			response.sendRedirect(ViewPath.INDEX_PAGE);
		} catch (ValidationException | DataException e) {
			LOG.warn(e.getMessage());
			
			session.setAttribute(Constants.ERROR_MESSAGE_NAME, e.getMessage());
			doGet(request, response);
		}
	}
}
