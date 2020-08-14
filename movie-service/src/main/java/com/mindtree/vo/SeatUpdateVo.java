package com.mindtree.vo;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

public class SeatUpdateVo {

	long showId;

	List<String> seatNumbers;

	public SeatUpdateVo() {
		// TODO Auto-generated constructor stub
	}

	public SeatUpdateVo(long showId, List<String> seatNumbers) {
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
