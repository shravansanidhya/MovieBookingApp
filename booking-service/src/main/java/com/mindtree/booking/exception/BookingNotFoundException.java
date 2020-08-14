package com.mindtree.booking.exception;

/**
 * @author M1049008
 *
 */
public class BookingNotFoundException extends BookingManagementException {

	private static final long serialVersionUID = 4245684248677227496L;

	public BookingNotFoundException() {
		super();
	}

	public BookingNotFoundException(String message) {
		super(message);
	}

}
