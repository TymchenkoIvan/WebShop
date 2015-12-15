package com.company.service.bean.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.company.constant.Constants;
import com.company.service.bean.Bean;
import com.company.service.bean.OrderDetailsFormBean;
import com.company.util.ClassNameUtil;

public class OrderDetailsFormBeanCreator extends RequestBeanCreator{
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	@Override
	public Bean create(HttpServletRequest request) {
		LOG.info("OrderDetailsFormBeanCreator.create(HttpServletRequest request)");

		OrderDetailsFormBean bean = new OrderDetailsFormBean();

		bean.setDelivery(request.getParameter(Constants.ORDER_DETAILS_FORM_DELIVERY));
		bean.setPayment(request.getParameter(Constants.ORDER_DETAILS_FORM_PAYMENT));

		return bean;
	}

}
