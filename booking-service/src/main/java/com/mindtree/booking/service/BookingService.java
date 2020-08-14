package com.mindtree.booking.service;

import java.util.List;

import com.mindtree.booking.entity.Booking;
import com.mindtree.booking.exception.BookingNotFoundException;

public interface BookingService {

	List<Booking> getBookingsById(long id) throws BookingNotFoundException;
	
	Booking saveBooking(Booking booking);

}
