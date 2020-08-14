package com.mindtree.booking.vo;

public class TheatreResponseVO {

	private long theatreId;
	private String theatreName;
	private String location;

	public TheatreResponseVO() {
	}

	public TheatreResponseVO(long theatreId, String theatreName, String location) {
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static TheatreResponseVO convertRequestToResponse(TheatreRequestVO requestVO) {
		return new TheatreResponseVO(requestVO.getTheatreId(), requestVO.getTheatreName(),
				requestVO.getTheatreLocation());
	}

}
