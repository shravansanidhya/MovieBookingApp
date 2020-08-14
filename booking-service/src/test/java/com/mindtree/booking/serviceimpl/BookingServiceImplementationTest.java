package com.mindtree.booking.serviceimpl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.mindtree.booking.entity.Booking;
import com.mindtree.booking.exception.BookingNotFoundException;
import com.mindtree.booking.message.PaymentMode;
import com.mindtree.booking.repository.BookingRepository;
import com.mindtree.booking.service.serviceimpl.BookingServiceImplementation;

/**
 * @author M1049008
 *
 */
public class BookingServiceImplementationTest {

	@Mock
	private BookingRepository bookingRepo;
	@InjectMocks
	private BookingServiceImplementation bookingServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void positiveTestGetBookingsById() throws BookingNotFoundException {
		List<String> booking1Seats = new ArrayList<>();
		booking1Seats.add("A1");
		booking1Seats.add("A2");
		booking1Seats.add("A3");

		List<String> booking2Seats = new ArrayList<>();
		booking2Seats.add("A7");

		Booking booking1 = new Booking();
		booking1.setId(76511);
		booking1.setUserId(121);
		booking1.setScreeningId(453);
		booking1.setMovieId(345);
		booking1.setTheatreId(4321);
		booking1.setBookingDate(LocalDate.of(2019, Month.SEPTEMBER, 19));
		booking1.setBookingTime(LocalTime.of(13, 45));
		booking1.setBookedSeats(booking1Seats);
		booking1.setTotalAmount(456.65F);
		booking1.setMode(PaymentMode.CREDIT_CARD);

		Booking booking2 = new Booking();
		booking2.setId(4324);
		booking2.setUserId(121);
		booking2.setScreeningId(532);
		booking2.setMovieId(123);
		booking2.setTheatreId(4321);
		booking2.setBookingDate(LocalDate.of(2018, Month.OCTOBER, 4));
		booking2.setBookingTime(LocalTime.of(12, 45));
		booking2.setBookedSeats(booking2Seats);
		booking2.setTotalAmount(256F);
		booking2.setMode(PaymentMode.CREDIT_CARD);

		List<Booking> allBookings = new ArrayList<>();
		allBookings.add(booking1);
		allBookings.add(booking2);

		Mockito.when(bookingRepo.findBookingsByUserId(121, Sort.by(Direction.DESC, "bookingDate", "bookingTime")))
				.thenReturn(allBookings);

		List<Booking> allResultBookings = bookingServiceImpl.getBookingsById(121);

		assertEquals(allBookings.size(), allResultBookings.size());

	}

	@Test(expected = BookingNotFoundException.class)
	public void negativeTestGetBookingsById() throws BookingNotFoundException {

		Mockito.when(bookingServiceImpl.getBookingsById(121)).thenThrow(BookingNotFoundException.class);

	}

	@Test
	public void testSaveBooking() {
		List<String> bookingSeats = new ArrayList<>();
		bookingSeats.add("A1");
		bookingSeats.add("A2");
		bookingSeats.add("A3");
		Booking booking = new Booking();
		booking.setId(4324);
		booking.setUserId(121);
		booking.setScreeningId(532);
		booking.setMovieId(123);
		booking.setTheatreId(4321);
		booking.setBookingDate(LocalDate.of(2018, Month.OCTOBER, 4));
		booking.setBookingTime(LocalTime.of(12, 45));
		booking.setBookedSeats(bookingSeats);
		booking.setTotalAmount(256F);
		booking.setMode(PaymentMode.CREDIT_CARD);

		Mockito.when(bookingRepo.save(booking)).thenReturn(booking);

		Booking bookingResult = bookingServiceImpl.saveBooking(booking);

		assertEquals(121, bookingResult.getUserId());
	}

}
