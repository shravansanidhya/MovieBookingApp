package com.mindtree.booking.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindtree.booking.message.PaymentMode;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "user_id")
	private long userId;
	
	@NotNull
	@Column(name = "screening_id")
	private long screeningId;
	
	@NotNull
	@Column(name = "theatre_id")
	private long theatreId;
	@NotNull
	@Column(name = "movie_id")
	private long movieId;
	@Column(name = "booking_date")
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "YYYY-MM-dd")
	private LocalDate bookingDate;
	@Column(name = "booking_time")
//	@DateTimeFormat(iso = ISO.TIME)
//	@JsonFormat(pattern = "HH:mm")
	private LocalTime bookingTime;
	@NotNull
	@ElementCollection
	@CollectionTable(name = "seats_booked", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "booked_seats")
	private List<String> bookedSeats;
	@NotNull
	@Column(name = "total_amount")
	private float totalAmount;
	@NotNull
	@Column(name = "payment_mode")
	private PaymentMode mode;

	public Booking() {
		super();
	}

	public Booking(long userId, long screeningId, long theatreId, long movieId, LocalDate bookingDate,
			LocalTime bookingTime, List<String> bookedSeats, float totalAmount, PaymentMode mode) {
		this.userId = userId;
		this.screeningId = screeningId;
		this.theatreId = theatreId;
		this.movieId = movieId;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.bookedSeats = bookedSeats;
		this.totalAmount = totalAmount;
		this.mode = mode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(long screeningId) {
		this.screeningId = screeningId;
	}

	public long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(long theatreId) {
		this.theatreId = theatreId;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public List<String> getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(List<String> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookedSeats == null) ? 0 : bookedSeats.hashCode());
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((bookingTime == null) ? 0 : bookingTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		result = prime * result + (int) (movieId ^ (movieId >>> 32));
		result = prime * result + (int) (screeningId ^ (screeningId >>> 32));
		result = prime * result + (int) (theatreId ^ (theatreId >>> 32));
		result = prime * result + Float.floatToIntBits(totalAmount);
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (bookedSeats == null) {
			if (other.bookedSeats != null)
				return false;
		} else if (!bookedSeats.equals(other.bookedSeats))
			return false;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (bookingTime == null) {
			if (other.bookingTime != null)
				return false;
		} else if (!bookingTime.equals(other.bookingTime))
			return false;
		if (id != other.id)
			return false;
		if (mode != other.mode)
			return false;
		if (movieId != other.movieId)
			return false;
		if (screeningId != other.screeningId)
			return false;
		if (theatreId != other.theatreId)
			return false;
		if (Float.floatToIntBits(totalAmount) != Float.floatToIntBits(other.totalAmount))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
