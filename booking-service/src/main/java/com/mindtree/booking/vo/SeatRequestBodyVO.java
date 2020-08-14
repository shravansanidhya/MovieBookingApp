package com.mindtree.booking.vo;

import java.util.List;

public class SeatRequestBodyVO {

	private long showId;
	private List<String> seatNumbers;

	public SeatRequestBodyVO() {
		super();
	}

	public SeatRequestBodyVO(long showId, List<String> seatNumbers) {
		super();
		this.showId = showId;
		this.seatNumbers = seatNumbers;
	}

	public long getShowId() {
		return showId;
	}

	public void setShowId(long showId) {
		this.showId = showId;
	}

	public List<String> getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

}
