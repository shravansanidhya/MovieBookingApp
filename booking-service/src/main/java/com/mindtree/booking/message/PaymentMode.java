package com.mindtree.booking.message;

public enum PaymentMode {

	DEBIT_CARD("Debit Card"), CREDIT_CARD("Credit Card"), UPI("UPI"), NET_BANKING("Net Banking");

	private String mode;

	private PaymentMode(String mode) {
		this.mode = mode;
	}

	public String getMode(String mode) {
		return this.mode;
	}

}
