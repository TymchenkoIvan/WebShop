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
import com.epam.preprod.tymchenko.entity.User;
import com.epam.preprod.tymchenko.exception.DataException;
import com.epam.preprod.tymchenko.exception.ValidationException;
import com.epam.preprod.tymchenko.service.OrderService;
import com.epam.preprod.tymchenko.service.bean.OrderDetailsFormBean;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;
import com.epam.preprod.tymchenko.util.BeanUtil;
import com.epam.preprod.tymchenko.util.ClassNameUtil;
import com.epam.preprod.tymchenko.constant.AppScopeConstants;
import com.epam.preprod.tymchenko.constant.Constants;
import com.epam.preprod.tymchenko.util.Validator;
import com.epam.preprod.tymchenko.util.ViewPath;

@SuppressWarnings("serial")
public class MakeOrderServlet extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	private OrderService orderService;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		orderService = (OrderService) servletConfig.getServletContext().getAttribute(AppScopeConstants.CONTEXT_ORDER_SERVICE);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doGet()");
		
		request.getRequestDispatcher(ViewPath.SUCCESS_PAGE).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("doPost()");

		HttpSession session;
		User user;
		Basket basket;
		OrderDetailsFormBean detailsBean;
		OrderInfoBean orderBean;
		long orderId;
		
		try{
			session = request.getSession();
			
			user = (User) session.getAttribute(Constants.WEB_USER);
			basket = (Basket) session.getAttribute(Constants.WEB_SESSION_BASKET);
			detailsBean = (OrderDetailsFormBean) session.getAttribute(Constants.WEB_SESSION_ORDER_DETAILS);
			
			orderBean = BeanUtil.createOrderInfoBean(basket, detailsBean, user);
			Validator.validate(orderBean);
			
			orderId = orderService.addAllOrderInfo(orderBean);
			
			session.removeAttribute(Constants.WEB_SESSION_BASKET);
			request.setAttribute(Constants.WEB_ORDER_ID, orderId);
			
			doGet(request, response);
		} catch(ValidationException | DataException | NullPointerException e){
			
			LOG.warn(e.getMessage());
			response.sendRedirect(ViewPath.DETAILS_PAGE);
		}
	}
}
