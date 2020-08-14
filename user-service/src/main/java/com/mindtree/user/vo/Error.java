package com.mindtree.user.vo;

import com.mindtree.user.message.ErrorCode;

public class Error {

	private ErrorCode errCode;
	private String errorMessage;

	public Error() {
		super();
	}

	public Error(ErrorCode errCode, String errorMessage) {
		this.errCode = errCode;
		this.errorMessage = errorMessage;
	}

	public ErrorCode getErrCode() {
		return errCode;
	}

	public void setErrCode(ErrorCode errCode) {
		this.errCode = errCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}