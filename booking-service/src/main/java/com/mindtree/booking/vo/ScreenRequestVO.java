package com.mindtree.booking.vo;

public class ScreenRequestVO {
	
	MovieRequestVO movie;
	
	TheatreRequestVO theatre;
	
	private String showTiming;

	public ScreenRequestVO() {
	}

	public ScreenRequestVO(MovieRequestVO movie, TheatreRequestVO theatre, String showTiming) {
		this.movie = movie;
		this.theatre = theatre;
		this.showTiming = showTiming;
	}

	public MovieRequestVO getMovie() {
		return movie;
	}

	public void setMovie(MovieRequestVO movie) {
		this.movie = movie;
	}

	public TheatreRequestVO getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreRequestVO theatre) {
		this.theatre = theatre;
	}

	public String getShowTiming() {
		return showTiming;
	}

	public void setShowTiming(String showTiming) {
		this.showTiming = showTiming;
	}

}
