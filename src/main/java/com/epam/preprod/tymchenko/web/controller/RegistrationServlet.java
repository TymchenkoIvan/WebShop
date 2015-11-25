package com.epam.preprod.tymchenko.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.exception.Messages;
import com.epam.preprod.tymchenko.exception.ValidationException;
import com.epam.preprod.tymchenko.service.UserService;
import com.epam.preprod.tymchenko.service.bean.RequestBeanType;
import com.epam.preprod.tymchenko.service.bean.RegistrationFormBean;
import com.epam.preprod.tymchenko.service.bean.factory.RequestBeanFactory;
import com.epam.preprod.tymchenko.service.captcha.captchaSaver.Saver;
import com.epam.preprod.tymchenko.constant.AppScopeConstants;
import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.util.ViewPath;
import com.epam.preprod.tymchenko.util.Validator;

/**
 * Obtains registration form reguest.
 * 
 * @author Ivan_Tymchenko
 */
@MultipartConfig(
		fileSizeThreshold = 1024*1024*1, 
		maxFileSize = 1024*1024*1, 
		maxRequestSize = 1024*1024*1)
@SuppressWarnings("serial")
public class RegistrationServlet extends HttpServlet {

	private UserService userService;
	private Saver captchaSaver;

	public void init(ServletConfig servletConfig) throws ServletException {
		userService = (UserService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_USER_SERVICE);
		captchaSaver = (Saver) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_CAPTCHA_SAVER);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(ViewPath.REGISTER_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		RegistrationFormBean bean = (RegistrationFormBean)RequestBeanFactory.createBean(RequestBeanType.REGISTER_FORM, request);

		try {
			Validator.validate(bean);
			Validator.validateCaptcha(request, bean, captchaSaver);

			if (!userService.isLoginUnique(bean.getLogin())) {
				throw new ValidationException(Messages.VALIDATION_LOGIN_NOT_UNIQUE);
			}
			
			saveAvatar(request, bean.getLogin());
			userService.addUser(userService.getUserFromBean(bean));
			
			session.removeAttribute(Constants.WEB_REGISTER_BEAN);
			session.removeAttribute(Constants.ERROR_MESSAGE_NAME);
			
			response.sendRedirect(ViewPath.LOGIN_PAGE);
			
		} catch (ValidationException | DataException e) {
			
			session.setAttribute(Constants.ERROR_MESSAGE_NAME, e.getMessage());
			session.setAttribute(Constants.WEB_REGISTER_BEAN, bean);
			
			doGet(request, response);
		}
	}

	private boolean saveAvatar(HttpServletRequest request, String dirName) throws IOException, IllegalStateException, ServletException {
		String savePath = Constants.IMAGE_DIR + File.separator + dirName + File.separator;

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
        
		Part part = request.getPart(Constants.FORM_AVATAR);
		part.write(savePath + Constants.AVATAR_FILE);

		return true;
	}
}