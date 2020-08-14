package com.mindtree.user.controller;

import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.user.exception.DuplicateExcepiton;
import com.mindtree.user.exception.NoUsersException;
import com.mindtree.user.exception.NotFoundException;
import com.mindtree.user.message.ErrorCode;
import com.mindtree.user.vo.Error;
import com.mindtree.user.vo.ResponseObject;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(DuplicateExcepiton.class)
	public ResponseObject<Object> duplicateUserException(DuplicateExcepiton e) {
		LOGGER.error(e.getMessage());
		ResponseObject<Object> response = new ResponseObject<>();
		response.setData(null);
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setUserMessage("User Already Exists");
		response.setError(new Error(ErrorCode.UMS101, ErrorCode.UMS101.getMessage()));
		return response;
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseObject<Object> noUserFoundException(NotFoundException e) {
		LOGGER.error(e.getMessage());
		ResponseObject<Object> response = new ResponseObject<>();
		response.setData(null);
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setUserMessage("User Does Not Exist");
		response.setError(new Error(ErrorCode.UMS105, ErrorCode.UMS105.getMessage()));
		return response;
	}

	@ExceptionHandler(NoUsersException.class)
	public ResponseObject<Object> noUserFoundException(NoUsersException e) {
		LOGGER.error(e.getMessage());
		ResponseObject<Object> response = new ResponseObject<>();
		response.setData(null);
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setUserMessage("User Database Is Empty");
		response.setError(new Error(ErrorCode.UMS102, ErrorCode.UMS102.getMessage()));
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
			userError = userError + objectError.getDefaultMessage() + " \n ";
			FieldError fe = (FieldError) objectError;
			fieldError += fe.getField() + " : " + fe.getDefaultMessage() + " \n ";
		}
		response.setUserMessage(userError);
		response.setError(new Error(ErrorCode.UMS104, fieldError));
		return response;
	}

	@ExceptionHandler(GenericJDBCException.class)
	public ResponseObject<Object> dataBaseException(GenericJDBCException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("Database Error");
		response.setError(new Error(ErrorCode.UMS103, ErrorCode.UMS103.getMessage()));
		return response;
	}

	@ExceptionHandler(JDBCConnectionException.class)
	public ResponseObject<Object> dataBaseException(JDBCConnectionException e) {
		ResponseObject<Object> response = new ResponseObject<>();
		LOGGER.error(e.getMessage());
		response.setData(null);
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setUserMessage("Database Communication Failure");
		response.setError(new Error(ErrorCode.UMS107, ErrorCode.UMS107.getMessage()));
		return response;
	}

}
