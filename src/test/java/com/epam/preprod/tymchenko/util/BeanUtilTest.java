package com.epam.preprod.tymchenko.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.preprod.tymchenko.entity.Basket;
import com.epam.preprod.tymchenko.entity.Delivery;
import com.epam.preprod.tymchenko.entity.Payment;
import com.epam.preprod.tymchenko.entity.User;
import com.epam.preprod.tymchenko.service.bean.JsonBasketBean;
import com.epam.preprod.tymchenko.service.bean.OrderDetailsFormBean;
import com.epam.preprod.tymchenko.service.bean.OrderInfoBean;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.HashMap;

public class BeanUtilTest {

	@Test
	public void isCreateJsonBasketBeanCorrect() {
		JsonBasketBean bean = new JsonBasketBean();
		
		Basket mockBasket  = mock(Basket.class);
		
		when(mockBasket.getProductsCount()).thenReturn(1);
		when(mockBasket.getTotalPrice()).thenReturn(new BigDecimal(10));
		
		bean = BeanUtil.createJsonBasketBean(mockBasket);
		
		assertTrue(bean.getTotalCount() == mockBasket.getProductsCount());
		assertEquals(bean.getTotalPrice(), mockBasket.getTotalPrice());
	}
	
	@Test
	public void isCreateJsonBasketBeanCorrectUsingSpy() {
		JsonBasketBean bean = new JsonBasketBean();
		
		Basket basket  = new Basket();
		Basket spyBasket = spy(basket);
		
		when(spyBasket.getProductsCount()).thenReturn(1);
		when(spyBasket.getTotalPrice()).thenReturn(new BigDecimal(10));
		
		bean = BeanUtil.createJsonBasketBean(spyBasket);
		
		assertTrue(bean.getTotalCount() == spyBasket.getProductsCount());
		assertEquals(bean.getTotalPrice(), spyBasket.getTotalPrice());
	}
	
	@Test
	public void isCreateOrderInfoBeanCorrect(){
		OrderInfoBean bean = new OrderInfoBean();
		
		Basket mockBasket  = mock(Basket.class);
		User mockUser  = mock(User.class);
		OrderDetailsFormBean mockOrderDetailsFormBean = mock(OrderDetailsFormBean.class);
		
		when(mockUser.getId()).thenReturn(1L);
		when(mockBasket.getMap()).thenReturn(new HashMap<>());
		when(mockOrderDetailsFormBean.getDelivery()).thenReturn(Delivery.ADDRESS.name());
		when(mockOrderDetailsFormBean.getPayment()).thenReturn(Payment.CARD.name());
		
		bean = BeanUtil.createOrderInfoBean(mockBasket, mockOrderDetailsFormBean, mockUser);
		
		assertTrue(bean.getUserId() == mockUser.getId());
		assertNotNull(bean.getMap());
		assertTrue(bean.getDeliveryId() == Delivery.getKeyByName(mockOrderDetailsFormBean.getDelivery()));
		assertTrue(bean.getPaymentId() == Payment.getKeyByName(mockOrderDetailsFormBean.getPayment()));
	}
}
