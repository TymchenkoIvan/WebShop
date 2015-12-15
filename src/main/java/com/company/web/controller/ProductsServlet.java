package com.company.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.constant.AppScopeConstants;
import com.company.constant.Constants;
import com.company.exception.DataException;
import com.company.exception.Messages;
import com.company.exception.ValidationException;
import com.company.service.ProductService;
import com.company.service.bean.ProductBean;
import com.company.service.bean.RequestBeanType;
import com.company.service.bean.SortFormBean;
import com.company.service.bean.factory.RequestBeanFactory;
import com.company.tymchenko.util.ClassNameUtil;
import com.company.tymchenko.util.Validator;
import com.company.tymchenko.util.ViewPath;

@SuppressWarnings("serial")
public class ProductsServlet extends HttpServlet{

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private ProductService productService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		productService = (ProductService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_PRODUCT_SERVICE);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SortFormBean sortBean = (SortFormBean)RequestBeanFactory.createBean(RequestBeanType.SORT_FORM, request);
		
		List<ProductBean> productBeans = new ArrayList<>();
		
		int productsCount = 0;
		int onPage = 0;
		int page = 0;
		int pages = 0;
		
		String url = null;
		
		try {
			Validator.validate(sortBean);
			
			onPage = setOnPage(sortBean);
			page = setPageNumber(sortBean);
			
			productService.generateSelectQuery(sortBean, page, onPage);
			
			productBeans = productService.getProductsByQuery();
			productsCount = productService.getProductsCountByQuery();
			
			pages = setPages(productsCount, onPage);
			url = getUrl(request);
			
		} catch (DataException e) {
			LOG.warn(e.getMessage());
		} catch (ValidationException e) {
			LOG.warn(e.getMessage());
			request.setAttribute(Constants.ERROR_MESSAGE_NAME, Messages.VALIDATION_SORT_WRONG);
		}

		request.setAttribute(Constants.WEB_OLD_URL, url);
		request.setAttribute(Constants.WEB_PAGES, pages);
		request.setAttribute(Constants.WEB_PAGE, page);
		request.setAttribute(Constants.WEB_COUNT_ON_PAGE, onPage);
		request.setAttribute(Constants.WEB_PRODUCTS_COUNT, productsCount);
		request.setAttribute(Constants.WEB_SORT_BEAN, sortBean);
		request.setAttribute(Constants.WEB_PRODUCT_BEANS, productBeans);
		request.getRequestDispatcher(ViewPath.PRODUCTS_PAGE).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private int setOnPage(SortFormBean sortBean) throws ValidationException{
		int res = Constants.DEFAULT_ITEMS_ON_PAGE;
		
		String onPage = sortBean.getOnPage();
		
		if(Validator.validatePositiveNumberOrEmpty(onPage)){
			res = Integer.parseInt(onPage);
		}
		return res;
	}
	
	private int setPageNumber(SortFormBean sortBean) throws ValidationException{
		int res = Constants.DEFAULT_PAGE;
		
		String page = sortBean.getPageNumber();
		
		if(Validator.validatePositiveNumberOrEmpty(page)){
			res = Integer.parseInt(page);
		}
		return res;
	}
	
	private int setPages(int productsCount, int onPage){
		
		if(productsCount % onPage == 0){
			return productsCount % onPage;
		} else{
			return productsCount / onPage + 1;
		}
	}
	
	private String getUrl(HttpServletRequest request){
		StringBuffer requestURL = request.getRequestURL();
		
		if (request.getQueryString() != null) {
		    requestURL.append("?").append(request.getQueryString());
		}

		return  requestURL.toString();
	}
}