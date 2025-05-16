package com.example.simpleweb.model;
 
public class PaymentResponseModel{
	private String orderDesciption;
	private String transactionId;
	private String paymentMethod;
	private String paymentId;
	private String token;
	private String vnPayResponseCode;
	private boolean susccess;
	public String getOrderDesciption() {
		return orderDesciption;
	}
	public void setOrderDesciption(String orderDesciption) {
		this.orderDesciption = orderDesciption;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getVnPayResponseCode() {
		return vnPayResponseCode;
	}
	public void setVnPayResponseCode(String vnPayResponseCode) {
		this.vnPayResponseCode = vnPayResponseCode;
	}
	public boolean isSusccess() {
		return susccess;
	}
	public void setSusccess(boolean susccess) {
		this.susccess = susccess;
	}


}