package com.company.service;

import javax.servlet.http.HttpSession;

import com.company.entity.Basket;
import com.company.service.bean.ProductBean;

public interface BasketService extends Service<Basket>{
	
	Basket getBasketFromSession(HttpSession session);

	Basket addProduct(Basket basket, ProductBean productBean);
	
	Basket minusProduct(Basket basket, long id);

	Basket deleteProduct(Basket basket, long id);
}
