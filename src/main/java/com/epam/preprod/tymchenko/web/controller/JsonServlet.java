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
import com.epam.preprod.tymchenko.service.ProductService;
import com.epam.preprod.tymchenko.service.bean.JsonBasketBean;
import com.epam.preprod.tymchenko.service.bean.ProductBean;
import com.epam.preprod.tymchenko.util.BeanUtil;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.AppScopeConstants;
import com.epam.preprod.tymchenko.constant.Constants;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class JsonServlet extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private ProductService productService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		productService = (ProductService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_PRODUCT_SERVICE);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		LOG.info("doPost()");
		
		String id;
		ProductBean bean;
		JsonBasketBean jsonBean;
		HttpSession session;
		Basket basket;
		
		id = (String) request.getParameter(Constants.PRODUCT_ID);
		session = request.getSession(true);
		basket = (Basket) session.getAttribute(Constants.WEB_SESSION_BASKET);
		
		if(basket == null){
			basket = new Basket();
		}
		
		try {
			bean = productService.getProductById(Long.parseLong(id));
			basket.addProduct(bean);
			
			session.setAttribute(Constants.WEB_SESSION_BASKET, basket);
			
		} catch (NumberFormatException | DataException e) {
			LOG.warn(e.getMessage());
		}
		
		jsonBean = BeanUtil.createJsonBasketBean(basket);
		
		Gson gson = new Gson();
		String json = gson.toJson(jsonBean);
		
		LOG.info(json);
		
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doGET()");
    }
}