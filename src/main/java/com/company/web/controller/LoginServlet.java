package com.company.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.company.constant.AppScopeConstants;
import com.company.constant.Constants;
import com.company.entity.User;
import com.company.exception.DataException;
import com.company.exception.Messages;
import com.company.exception.ValidationException;
import com.company.service.UserService;
import com.company.service.bean.LoginFormBean;
import com.company.service.bean.RequestBeanType;
import com.company.service.bean.factory.RequestBeanFactory;
import com.company.util.ClassNameUtil;
import com.company.util.Validator;
import com.company.util.ViewPath;


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
