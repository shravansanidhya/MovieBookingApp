package com.mindtree.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.booking.message.ErrorCode;
import com.mindtree.booking.vo.Error;
import com.mindtree.booking.vo.ResponseObject;

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
		response.setError(new Error(ErrorCode.BMS306, ErrorCode.BMS306.getMessage()));
		return response;
	}

}
