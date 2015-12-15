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
import com.company.entity.Basket;
import com.company.service.BasketService;
import com.company.util.ClassNameUtil;
import com.company.util.ViewPath;

@SuppressWarnings("serial")
public class BasketDeleteServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private BasketService basketService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		LOG.trace("init(ServletConfig servletConfig)");
		
		basketService = (BasketService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_BASKET_SERVICE);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("doGet()");

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("doPost()");
		
		HttpSession session;
		Basket basket;

		String id = request.getParameter(Constants.PRODUCT_ID);
		
		session = request.getSession(true);
		basket = basketService.getBasketFromSession(session);
		
		if(basket == null){
			basket = new Basket();
		}
		
		try {			
			basket = basketService.deleteProduct(basket, Long.parseLong(id));
			
			session.setAttribute(Constants.WEB_SESSION_BASKET, basket);
			
		} catch (NumberFormatException e) {
			LOG.warn(e.getMessage());
		}
		
		LOG.info("Basket.map.size: " + basket.getMap().size());
		response.sendRedirect(ViewPath.BASKET_PAGE);
	}
}
