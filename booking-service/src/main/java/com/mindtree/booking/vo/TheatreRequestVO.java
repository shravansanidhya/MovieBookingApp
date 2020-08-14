package com.mindtree.booking.vo;

public class TheatreRequestVO {

	private long theatreId;
	private String theatreName;
	private String theatreLocation;
	private String theatreAddress;

	public TheatreRequestVO() {
	}

	public TheatreRequestVO(long theatreId, String theatreName, String theatreLocation, String theatreAddress) {
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreLocation = theatreLocation;
		this.theatreAddress = theatreAddress;
	}

	public long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(long theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreLocation() {
		return theatreLocation;
	}

	public void setTheatreLocation(String theatreLocation) {
		this.theatreLocation = theatreLocation;
	}

	public String getTheatreAddress() {
		return theatreAddress;
	}

	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}

}
