package com.mindtree.booking.vo;

/**
 * @author M1049008
 *
 */
public class SeatRequestVO {

	private int seatId;

	private String seatNumber;

	private String seatClass;

	private String seatStatus;

	public SeatRequestVO() {
		super();
	}

	public SeatRequestVO(int seatId, String seatNumber, String seatClass, String seatStatus) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.seatStatus = seatStatus;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatClass == null) ? 0 : seatClass.hashCode());
		result = prime * result + seatId;
		result = prime * result + ((seatNumber == null) ? 0 : seatNumber.hashCode());
		result = prime * result + ((seatStatus == null) ? 0 : seatStatus.hashCode());
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
		SeatRequestVO other = (SeatRequestVO) obj;
		if (seatClass == null) {
			if (other.seatClass != null)
				return false;
		} else if (!seatClass.equals(other.seatClass))
			return false;
		if (seatId != other.seatId)
			return false;
		if (seatNumber == null) {
			if (other.seatNumber != null)
				return false;
		} else if (!seatNumber.equals(other.seatNumber))
			return false;
		if (seatStatus == null) {
			if (other.seatStatus != null)
				return false;
		} else if (!seatStatus.equals(other.seatStatus))
			return false;
		return true;
	}

}
