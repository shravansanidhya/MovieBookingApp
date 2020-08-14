package com.mindtree.booking.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.mindtree.booking.message.PaymentMode;

public class BookingResponseVO {

	private long id;
//	private long userId;
	private ScreenResponseVO screening;
	private LocalDate bookingDate;
	private LocalTime bookingTime;
	private List<String> bookedSeats;
	private float totalAmount;
	private PaymentMode mode;

	public BookingResponseVO() {
	}

	public BookingResponseVO(long id, ScreenResponseVO screening, LocalDate bookingDate, LocalTime bookingTime,
			List<String> bookedSeats, float totalAmount, PaymentMode mode) {
		this.id = id;
		this.screening = screening;
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

	public ScreenResponseVO getScreening() {
		return screening;
	}

	public void setScreening(ScreenResponseVO screening) {
		this.screening = screening;
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

}
