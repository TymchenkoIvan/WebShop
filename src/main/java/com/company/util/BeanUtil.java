package com.company.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.entity.Basket;
import com.company.entity.Brand;
import com.company.entity.Delivery;
import com.company.entity.Payment;
import com.company.entity.Product;
import com.company.entity.Type;
import com.company.entity.User;
import com.company.service.bean.JsonBasketBean;
import com.company.service.bean.OrderDetailsFormBean;
import com.company.service.bean.OrderInfoBean;
import com.company.service.bean.ProductBean;

public class BeanUtil {
	
	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());
	
	public static JsonBasketBean createJsonBasketBean(Basket basket) {
		LOG.info("BeanUtil.createJsonBasketBean(Basket basket)");

		JsonBasketBean bean = new JsonBasketBean();

		bean.setTotalCount(basket.getProductsCount());
		bean.setTotalPrice(basket.getTotalPrice());

		return bean;
	}
	
	public static OrderInfoBean createOrderInfoBean(Basket basket, OrderDetailsFormBean detailsBean, User user) {
		LOG.info("BeanUtil.createOrderInfoBean(Basket basket, OrderDetailsFormBean detailsBean)");

		OrderInfoBean bean = new OrderInfoBean();
		
		bean.setUserId(user.getId());
		bean.setMap(basket.getMap());
		bean.setDeliveryId(Delivery.getKeyByName(detailsBean.getDelivery()));
		bean.setPaymentId(Payment.getKeyByName(detailsBean.getPayment()));

		return bean;
	}

	public static List<ProductBean> createProductBeanList(List<Product> products) {
		LOG.info("BeanUtil.createProductBeanList(List<Product> products)");

		List<ProductBean> productBeans = new ArrayList<>();

		for (Product product : products) {
			productBeans.add(createProductBeanFromProduct(product));
		}

		return productBeans;
	}

	public static ProductBean createProductBeanFromProduct(Product product) {
		LOG.info("BeanUtil.createProductBeanFromProduct(Product product)");

		ProductBean bean = new ProductBean();

		bean.setId(product.getId());
		bean.setName(product.getName());
		bean.setText(product.getText());
		bean.setBrand(Brand.getNameByKey((int) product.getBrandId()));
		bean.setType(Type.getNameByKey((int) product.getTypeId()));
		bean.setPrice(product.getPrice());

		return bean;
	}
}
