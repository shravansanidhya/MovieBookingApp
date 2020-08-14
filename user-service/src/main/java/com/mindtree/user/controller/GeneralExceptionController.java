package com.mindtree.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.user.message.ErrorCode;
import com.mindtree.user.vo.ResponseObject;
import com.mindtree.user.vo.Error;

@RestControllerAdvice
//@Priority(value = 1)
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class GeneralExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ResponseObject<Object> mainException(Exception e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage(
				"Something Is Not Working As It Should, We'll Bring In A Technician To Look At It. Appreciate Your Patience");
		response.setError(new Error(ErrorCode.UMS106, ErrorCode.UMS106.getMessage()));
		return response;
	}

}
