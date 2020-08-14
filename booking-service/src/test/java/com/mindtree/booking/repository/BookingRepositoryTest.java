package com.mindtree.booking.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.booking.entity.Booking;
import com.mindtree.booking.message.PaymentMode;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BookingRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	@Autowired
	BookingRepository bookingRepo;

	@Test
	public void saveTest() {

		List<String> bookingSeats = new ArrayList<>();
		bookingSeats.add("A1");
		bookingSeats.add("A2");
		bookingSeats.add("A3");

		Booking booking = new Booking();
		booking.setUserId(121);
		booking.setScreeningId(453);
		booking.setMovieId(345);
		booking.setTheatreId(4321);
		booking.setBookingDate(LocalDate.of(2019, Month.SEPTEMBER, 19));
		booking.setBookingTime(LocalTime.of(13, 45));
		booking.setBookedSeats(bookingSeats);
		booking.setTotalAmount(456.65F);
		booking.setMode(PaymentMode.CREDIT_CARD);

		entityManager.persistAndFlush(booking);
		assertThat(bookingRepo.findById(booking.getId()).get()).isEqualTo(booking);
	}

	@Test
	public void findBookingsByUserIdTest() {
		List<String> booking1Seats = new ArrayList<>();
		booking1Seats.add("A1");
		booking1Seats.add("A2");
		booking1Seats.add("A3");

		List<String> booking2Seats = new ArrayList<>();
		booking2Seats.add("A7");

		Booking booking1 = new Booking();
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

		entityManager.persistAndFlush(booking1);
		entityManager.persistAndFlush(booking2);

		assertThat(bookingRepo.findBookingsByUserId(121, Sort.by(Direction.DESC, "bookingDate", "bookingTime")))
				.isEqualTo(allBookings);

	}

}
