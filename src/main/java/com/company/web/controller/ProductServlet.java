package com.company.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.constant.AppScopeConstants;
import com.company.constant.Constants;
import com.company.exception.DataException;
import com.company.exception.ValidationException;
import com.company.service.MySqlProductService;
import com.company.service.bean.ProductBean;
import com.company.tymchenko.util.ClassNameUtil;
import com.company.tymchenko.util.Validator;
import com.company.tymchenko.util.ViewPath;

@SuppressWarnings("serial")
public class ProductServlet extends HttpServlet{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private MySqlProductService productService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		productService = (MySqlProductService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_PRODUCT_SERVICE);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doGet()");
		
		String id = request.getParameter(Constants.PRODUCT_ID);
		String url = getUrl(request);
		
		ProductBean productBean = new ProductBean();
		
		try {
			Validator.validatePositiveNumberOrEmpty(id);
			
			productBean = productService.getProductById(Long.parseLong(id));
			
		} catch (DataException e) {
			LOG.warn(e.getMessage());
		} catch (ValidationException e) {
			e.printStackTrace();
			LOG.warn(e.getMessage());
			request.setAttribute(Constants.ERROR_MESSAGE_NAME, e.getMessage());
		}

		request.setAttribute(Constants.WEB_OLD_URL, url);
		request.setAttribute(Constants.WEB_PRODUCT_BEAN, productBean);
		request.getRequestDispatcher(ViewPath.PRODUCT_PAGE).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doPost()");
		
		doGet(request, response);
	}
	
	private String getUrl(HttpServletRequest request){
		StringBuffer requestURL = request.getRequestURL();
		
		if (request.getQueryString() != null) {
		    requestURL.append("?").append(request.getQueryString());
		}

		return  requestURL.toString();
	}
}
