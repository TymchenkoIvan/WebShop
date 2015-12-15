package com.company.service;

import javax.servlet.http.HttpSession;

import com.company.constant.Constants;
import com.company.entity.Basket;
import com.company.service.bean.ProductBean;

public class MySqlBasketService implements BasketService{

	@Override
	public Basket getBasketFromSession(HttpSession session) {
		
		return (Basket) session.getAttribute(Constants.WEB_SESSION_BASKET);
	}

	@Override
	public Basket minusProduct(Basket basket, long id) {
		
		basket.minusProduct(id);
		return basket;
	}

	@Override
	public Basket addProduct(Basket basket, ProductBean productBean) {
		
		basket.addProduct(productBean);
		return basket;
	}

	@Override
	public Basket deleteProduct(Basket basket, long id) {

		basket.deleteProduct(id);
		return basket;
	}

}