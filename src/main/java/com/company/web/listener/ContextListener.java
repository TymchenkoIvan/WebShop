package com.company.web.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.company.constant.AppScopeConstants;
import com.company.constant.DeployConstants;
import com.company.entity.security.Security;
import com.company.service.MySqlBasketService;
import com.company.service.MySqlOrderService;
import com.company.service.MySqlProductService;
import com.company.service.MySqlUserService;
import com.company.service.captcha.Captcha;
import com.company.service.captcha.captchaSaver.SaverFactory;
import com.company.service.captcha.captchaSaver.SaverType;
import com.company.util.ClassNameUtil;
import com.company.util.locale.handlerfactory.LocaleHandlerFactory;
import com.company.util.locale.handlerfactory.LocaleHandlerType;
import com.company.web.filter.access.SecurityDispatcher;
import com.company.web.filter.access.SecurityXmlParser;

/**
 * ContextListener for application.
 * 
 * @author Ivan_Tymchenko
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	private String resourcesFolder;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOG.info("ContextListener.contextDestroyed(ServletContextEvent event)");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("ContextListener.contextInitialized(ServletContextEvent event)");

		ServletContext context = event.getServletContext();
		
		resourcesFolder = context.getRealPath("") + File.separator + context.getInitParameter(DeployConstants.WEB_XML_RESOURCE_FOLDER);

		initCaptchaConfig(event);
		initCaptchaSaver(event);
		initLocaleHandler(event);
		initCookieTimeOut(event);
		initSecurityDispatcher(event);

		initUserService(context);
		initProductService(context);
		initOrderService(context);
		initBasketService(context);
	}
	
	private void initBasketService(ServletContext context) {
		LOG.info("ContextListener.initBasketService(ServletContext context)");

		context.setAttribute(AppScopeConstants.CONTEXT_BASKET_SERVICE, new MySqlBasketService());
	}

	private void initOrderService(ServletContext context) {
		LOG.info("ContextListener.initOrderService(ServletContext context)");

		context.setAttribute(AppScopeConstants.CONTEXT_ORDER_SERVICE, new MySqlOrderService());
	}

	private void initUserService(ServletContext context) {
		LOG.info("ContextListener.initUserService(ServletContext context)");

		context.setAttribute(AppScopeConstants.CONTEXT_USER_SERVICE, new MySqlUserService());
	}

	private void initProductService(ServletContext context) {
		LOG.info("ContextListener.initProductService(ServletContext context)");

		context.setAttribute(AppScopeConstants.CONTEXT_PRODUCT_SERVICE, new MySqlProductService());
	}

	private void initCaptchaConfig(ServletContextEvent sce) {
		LOG.info("ContextListener.initCaptchaConfig(ServletContextEvent sce)");

		long timeout = Long
				.parseLong(sce.getServletContext().getInitParameter(DeployConstants.WEB_XML_CAPTCHA_TIMEOUT));
		Captcha.initTimeOut(timeout);
	}

	private void initCookieTimeOut(ServletContextEvent sce) {
		LOG.info("ContextListener.initCookieTimeOut(ServletContextEvent sce)");

		int timeout = Integer
				.parseInt(sce.getServletContext().getInitParameter(DeployConstants.WEB_XML_CAPTCHA_TIMEOUT));

		sce.getServletContext().setAttribute(AppScopeConstants.CONTEXT_COOKIE_TIMEOUT, timeout);
	}

	private void initCaptchaSaver(ServletContextEvent sce) {
		LOG.info("ContextListener.initCaptchaSaver(ServletContextEvent sce)");

		SaverType type = SaverType
				.valueOf(sce.getServletContext().getInitParameter(DeployConstants.WEB_XML_CAPTCHA_SAVER).toUpperCase());
		sce.getServletContext().setAttribute(AppScopeConstants.CONTEXT_CAPTCHA_SAVER, SaverFactory.SAVERS.get(type));
	}

	private void initLocaleHandler(ServletContextEvent sce) {
		LOG.info("ContextListener.initLocaleHandler(ServletContextEvent sce)");

		LocaleHandlerType type = LocaleHandlerType
				.valueOf(sce.getServletContext().getInitParameter(DeployConstants.WEB_XML_LOCALE_SAVER).toUpperCase());
		sce.getServletContext().setAttribute(AppScopeConstants.CONTEXT_LOCALE_HANDLER, LocaleHandlerFactory.HANDLERS.get(type));
	}

	private void initSecurityDispatcher(ServletContextEvent sce) {
		LOG.info("ContextListener.initSecurityDispatcher(ServletContextEvent sce)");

		String securityFilePath = resourcesFolder + sce.getServletContext().getInitParameter(DeployConstants.WEB_XML_SECURITY_FILE_PATH);
		Security security = new SecurityXmlParser().parse(securityFilePath);
		SecurityDispatcher securityDispatcher = new SecurityDispatcher(security);

		sce.getServletContext().setAttribute(AppScopeConstants.CONTEXT_SECURITY_DISPATCHER, securityDispatcher);
	}
}
