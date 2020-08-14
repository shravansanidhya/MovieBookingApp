package com.mindtree.user.message;

public enum ErrorCode {

	UMS101("Duplicate User"), UMS102("Database Empty"), UMS103("Database Persist Error"),
	UMS104("Validation Error"), UMS105("Resource Not Found"), UMS106("Something Went Wrong"), UMS107("Database Connection Error");

	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.code;
	}

}
