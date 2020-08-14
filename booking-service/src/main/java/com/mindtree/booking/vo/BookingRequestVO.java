package com.mindtree.booking.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindtree.booking.message.PaymentMode;

public class BookingRequestVO {

	@NotNull(message = "User Id Cannot Be Empty")
	@Positive(message = "User Id Must Be Positive Integers")
	private long userId;

	@NotNull(message = "Show Id Cannot Be Empty")
	@Positive(message = "Show Id Must Be Positive Integers")
	private long screeningId;

	@NotNull(message = "Theatre Id Cannot Be Empty")
	@Positive(message = "Theatre Id Must Be Positive Integers")
	private long theatreId;

	@NotNull(message = "Movie Id Cannot Be Empty")
	@Positive(message = "Movie Id Must Be Positive Integers")
	private long movieId;

	@NotNull(message = "Date Cannot Be Empty")
	@DateTimeFormat(iso = ISO.DATE)
	@FutureOrPresent(message = "Booking Date Cannot Be From Past")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;

	@NotNull(message = "Time Cannot Be Empty")
	@DateTimeFormat(iso = ISO.TIME)
	@JsonFormat(pattern = "HH:mm")
	private LocalTime bookingTime;

	@NotNull(message = "Seats Cannot Be Empty")
	private List<String> bookedSeats;

	@NotNull(message = "Seats Cannot Be Empty")
	@Positive(message = "Total Amount Must Be Positive")
	@Min(value = 50, message = "Invalid Price")
	private float totalAmount;

	@NotNull(message = "Mode Cannot Be Empty")
//	@Pattern(regexp = "DEBIT_CARD|CREDIT_CARD|UPI|NET_BANKING", message = "Mode Must Be One Of DEBIT_CARD, CREDIT_CARD, UPI, NET_BANKING")
	private PaymentMode mode;

	public BookingRequestVO() {
	}

	public BookingRequestVO(long userId, long screeningId, long theatreId, long movieId, LocalDate bookingDate,
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
		BookingRequestVO other = (BookingRequestVO) obj;
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

	@Override
	public String toString() {
		return "BookingRequestVO [userId=" + userId + ", screeningId=" + screeningId + ", theatreId=" + theatreId
				+ ", movieId=" + movieId + ", bookingDate=" + bookingDate + ", bookingTime=" + bookingTime
				+ ", bookedSeats=" + bookedSeats + ", totalAmount=" + totalAmount + ", mode=" + mode + "]";
	}

}
