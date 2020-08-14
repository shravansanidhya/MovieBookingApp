package com.mindtree.controller;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.exception.AddMovieException;
import com.mindtree.exception.DatabaseException;
import com.mindtree.exception.GetMovieException;
import com.mindtree.exception.NoRecordException;
import com.mindtree.exception.RecordAlreadyExistsException;
import com.mindtree.exception.RecordNotCreatedException;
import com.mindtree.exception.RecordNotFoundException;
import com.mindtree.exception.RecordNotModified;
import com.mindtree.exception.ShowNotFoundException;
import com.mindtree.exception.TheatreServiceException;
import com.mindtree.service.ErrorService;
import com.mindtree.vo.ResponseObject;

@RestControllerAdvice
public class BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/** The error service */
	@Autowired
	private ErrorService errorService;

	/**
	 * Handles Exception thrown from Controllers
	 * 
	 * @return ResponseObject<Object>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseObject<Object> controllerExceptionHandler(Exception exception) {
		logger.trace("-> entering controllerExceptionHandler: ");
		if (exception instanceof AddMovieException) {
			return errorService.createErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
					"Problem in database", exception.getMessage());
		} else if (exception instanceof GetMovieException) {
			return errorService.createErrorResponse(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT.value(),
					"Object not found in database", exception.getMessage());
		} else if (exception instanceof RecordNotModified) {
			return errorService.createErrorResponse(HttpStatus.NOT_MODIFIED.name(), HttpStatus.NOT_MODIFIED.value(),
					"Object not found in database", exception.getMessage());
		} else if (exception instanceof RecordAlreadyExistsException) {
			return errorService.createErrorResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(),
					"Object exists", exception.getMessage());
		} else if (exception instanceof DatabaseException) {
			return errorService.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "connection problem", exception.getMessage());
		} else if (exception instanceof NoRecordException) {
			return errorService.createErrorResponse(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT.value(),
					"empty database", exception.getMessage());
		} else if (exception instanceof EntityNotFoundException) {
			return errorService.createErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
					"entity does not exist", exception.getMessage());
		}

		else if (exception instanceof ShowNotFoundException) {
			return errorService.createErrorResponse(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
					"entity does not exist", exception.getMessage());
		}

		else if (exception instanceof RecordNotCreatedException) {
			return errorService.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problem while adding", exception.getMessage());
		} else if (exception instanceof RecordNotFoundException) {
			return errorService.createErrorResponse(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT.value(),
					"Problem while findings theatre ", exception.getMessage());
		} else if (exception instanceof TheatreServiceException) {
			return errorService.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problem while fetching theatres",
					exception.getMessage());
		} else if (exception instanceof Exception) {
			return errorService.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "some internal error", exception.getMessage());
		}
		return null;
	}
}
