package com.epam.preprod.tymchenko.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.entity.Basket;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.service.BasketService;
import com.epam.preprod.tymchenko.service.ProductService;
import com.epam.preprod.tymchenko.service.bean.ProductBean;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.AppScopeConstants;
import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.util.ViewPath;

@SuppressWarnings("serial")
public class BasketAddServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private ProductService productService;
	private BasketService basketService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		LOG.trace("init(ServletConfig servletConfig)");
		
		productService = (ProductService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_PRODUCT_SERVICE);
		basketService = (BasketService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_BASKET_SERVICE);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("doGet()");

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("doPost()");
		
		ProductBean productBean;
		HttpSession session;
		Basket basket;

		String id = request.getParameter(Constants.PRODUCT_ID);
		
		session = request.getSession(true);
		basket = basketService.getBasketFromSession(session);
		
		if(basket == null){
			basket = new Basket();
		}
		
		try {
			productBean = productService.getProductById(Long.parseLong(id));
			
			basket = basketService.addProduct(basket, productBean);
			
			session.setAttribute(Constants.WEB_SESSION_BASKET, basket);
			
		} catch (NumberFormatException | DataException e) {
			LOG.warn(e.getMessage());
		}
		
		response.sendRedirect(ViewPath.BASKET_PAGE);
	}
}
