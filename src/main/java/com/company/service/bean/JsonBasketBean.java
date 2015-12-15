package com.company.service.bean;

import java.math.BigDecimal;

public class JsonBasketBean implements Bean{

	private static final long serialVersionUID = 8881230717845867867L;
	
	private int totalCount;
	private BigDecimal totalPrice;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
