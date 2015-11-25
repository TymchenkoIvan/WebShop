package com.epam.preprod.tymchenko.service.bean;

public class OrderDetailsFormBean implements Bean{

	private static final long serialVersionUID = 5692820726568472081L;
	
	private String delivery;
	private String payment;
	
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
}
