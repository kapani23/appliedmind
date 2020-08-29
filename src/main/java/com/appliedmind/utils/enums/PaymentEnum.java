package com.appliedmind.utils.enums;

public enum PaymentEnum {

	PAYTM("PayTm", "1"), GPAY("Google Pay", "2"), MASTERCARD("Mastercard", "3"), VISA("Visa", "4");

	private String paymentProviderDisplayName = null;
	private String paymentProviderCode = null;

	private PaymentEnum(String paymentProviderName, String paymentProviderCode) {
		this.paymentProviderCode = paymentProviderCode;
		this.paymentProviderDisplayName = paymentProviderName;
	}

	public String getPaymentProviderDisplayName() {
		return paymentProviderDisplayName;
	}

	public void setPaymentProviderDisplayName(String paymentProviderDisplayName) {
		this.paymentProviderDisplayName = paymentProviderDisplayName;
	}

	public String getPaymentProviderCode() {
		return paymentProviderCode;
	}

	public void setPaymentProviderCode(String paymentProviderCode) {
		this.paymentProviderCode = paymentProviderCode;
	}

}
