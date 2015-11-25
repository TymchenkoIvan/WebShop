package com.epam.preprod.tymchenko.service;

import javax.servlet.http.HttpSession;

import com.epam.preprod.tymchenko.entity.Basket;
import com.epam.preprod.tymchenko.service.bean.ProductBean;

public interface BasketService extends Service<Basket>{
	
	Basket getBasketFromSession(HttpSession session);

	Basket addProduct(Basket basket, ProductBean productBean);
	
	Basket minusProduct(Basket basket, long id);

	Basket deleteProduct(Basket basket, long id);
}
