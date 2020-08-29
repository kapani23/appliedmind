package com.appliedmind.search;

public class PaymentInfo {

	private String id = null;
	private String paymentType = null;

	public PaymentInfo(String id, String paymentType) {
		this.id = id;
		this.paymentType = paymentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "PaymentInfo [id=" + id + ", paymentType=" + paymentType + "]";
	}

}
