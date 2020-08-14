package com.mindtree.service;

import org.springframework.stereotype.Service;

import com.mindtree.vo.Error;
import com.mindtree.vo.ResponseObject;

@Service
public class ErrorService {
	public ResponseObject<Object> createErrorResponse(String message, int statusCode, String errorDescription,
			String errorMessage) {
		ResponseObject<Object> responseObject = new ResponseObject<Object>();
		responseObject.setUserMessage(message);
		responseObject.setStatusCode(statusCode);

		Error error = new Error();
		error.setDescription(errorDescription);
		error.setErrorMessage(errorMessage);

		responseObject.setError(error);
		return responseObject;

	}
}
