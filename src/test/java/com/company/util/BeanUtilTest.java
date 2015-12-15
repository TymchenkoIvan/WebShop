package com.company.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.company.entity.Basket;
import com.company.entity.Delivery;
import com.company.entity.Payment;
import com.company.entity.User;
import com.company.service.bean.JsonBasketBean;
import com.company.service.bean.OrderDetailsFormBean;
import com.company.service.bean.OrderInfoBean;
import com.company.service.bean.ProductBean;
import com.company.util.BeanUtil;

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
		when(mockBasket.getMap()).thenReturn(new HashMap<ProductBean, Integer>());
		when(mockOrderDetailsFormBean.getDelivery()).thenReturn(Delivery.ADDRESS.name());
		when(mockOrderDetailsFormBean.getPayment()).thenReturn(Payment.CARD.name());
		
		bean = BeanUtil.createOrderInfoBean(mockBasket, mockOrderDetailsFormBean, mockUser);
		
		assertTrue(bean.getUserId() == mockUser.getId());
		assertNotNull(bean.getMap());
		assertTrue(bean.getDeliveryId() == Delivery.getKeyByName(mockOrderDetailsFormBean.getDelivery()));
		assertTrue(bean.getPaymentId() == Payment.getKeyByName(mockOrderDetailsFormBean.getPayment()));
	}
}
