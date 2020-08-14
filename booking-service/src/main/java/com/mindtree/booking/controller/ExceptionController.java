package com.mindtree.booking.controller;

import java.net.ConnectException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.mindtree.booking.exception.BookingNotFoundException;
import com.mindtree.booking.message.ErrorCode;
import com.mindtree.booking.vo.Error;
import com.mindtree.booking.vo.ResponseObject;

@RestControllerAdvice
//@Priority(value = 0)
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseObject<Object> bookingNotFound(BookingNotFoundException e) {
		LOGGER.error(e.getMessage());
		ResponseObject<Object> response = new ResponseObject<>();
		response.setData(null);
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setUserMessage("No Booking Found!");
		response.setError(new Error(ErrorCode.BMS305, ErrorCode.BMS305.getMessage()));
		return response;
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseObject<Object> validationException(ConstraintViolationException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		response.setData(null);
		LOGGER.error(e.getMessage());
		String error = "";
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setUserMessage("Some error Ass");
		for (ConstraintViolation<?> vio : e.getConstraintViolations()) {
			error.concat(vio.getMessage() + ", ");
		}
		response.setUserMessage(error);
		response.setError(new Error(ErrorCode.BMS304, ErrorCode.BMS304.getMessage()));
		return response;
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseObject<Object> validationException(MethodArgumentNotValidException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		response.setData(null);
		String userError = "/ ";
		String fieldError = "/ ";
		LOGGER.error(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
			userError += objectError.getDefaultMessage() + " \n ";
			FieldError fe = (FieldError) objectError;
			fieldError += fe.getField() + ": " + objectError.getDefaultMessage() + " \n ";
		}
		response.setUserMessage(userError);
		response.setError(new Error(ErrorCode.BMS304, fieldError));
		return response;
	}

	@ExceptionHandler(ConnectException.class)
	public ResponseObject<Object> connectionFailure(ConnectException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("One Of The Service is Down.");
		response.setError(new Error(ErrorCode.BMS301, ErrorCode.BMS301.getMessage()));
		return response;
	}

	@ExceptionHandler(GenericJDBCException.class)
	public ResponseObject<Object> dataBaseException(GenericJDBCException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("Database Error");
		response.setError(new Error(ErrorCode.BMS303, ErrorCode.BMS303.getMessage()));
		return response;
	}

	@ExceptionHandler(JDBCConnectionException.class)
	public ResponseObject<Object> dataBaseException(JDBCConnectionException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("Database Communication Failure");
		response.setError(new Error(ErrorCode.BMS302, ErrorCode.BMS302.getMessage()));
		return response;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseObject<Object> parseExcdeption(HttpMessageNotReadableException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("Date Parse Error");
		response.setError(new Error(ErrorCode.BMS307, ErrorCode.BMS307.getMessage()));
		return response;
	}

	@ExceptionHandler(MismatchedInputException.class)
	public ResponseObject<Object> parseExcdeption(MismatchedInputException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("Time Parse Error");
		response.setError(new Error(ErrorCode.BMS307, ErrorCode.BMS307.getMessage()));
		return response;
	}
}
