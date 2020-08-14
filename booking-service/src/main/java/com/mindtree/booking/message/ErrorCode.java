package com.mindtree.booking.message;

public enum ErrorCode {

	BMS301("Service Down"), BMS302("Database Connection Error"), BMS303("Database Persist Error"),
	BMS304("Validation Error"), BMS305("Resource Not Found"), BMS306("Something Went Wrong"),
	BMS307("Date Parse Exception"), BMS308("Time Parse Exception");

	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.code;
	}

}
