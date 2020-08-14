package com.mindtree.booking.service.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mindtree.booking.entity.Booking;
import com.mindtree.booking.exception.BookingNotFoundException;
import com.mindtree.booking.repository.BookingRepository;
import com.mindtree.booking.service.BookingService;

@Service
public class BookingServiceImplementation implements BookingService {

	@Autowired
	BookingRepository bookingRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImplementation.class);

	@Override
	public List<Booking> getBookingsById(long userId) throws BookingNotFoundException {
		List<Booking> userBookings = bookingRepository.findBookingsByUserId(userId,
				Sort.by(Direction.DESC, "bookingDate", "bookingTime"));
		if (userBookings.isEmpty()) {
			LOGGER.error("No Bookings exists for user id " + userId + " !");
			throw new BookingNotFoundException("No Bookings exists for user id " + userId + " !");
		}
		LOGGER.info(userBookings.size() + " bookings found for user id " + userId + "!");
		return userBookings;
	}

	@Override
	public Booking saveBooking(Booking booking) {
		Booking savedBooking = bookingRepository.save(booking);
		return savedBooking;
	}

}
