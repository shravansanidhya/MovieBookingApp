package com.mindtree.booking.vo;

public class ScreenResponseVO {

	private MovieResponseVO movie;

	private TheatreResponseVO theatre;

	private String time;

	public ScreenResponseVO() {
	}

	public ScreenResponseVO(MovieResponseVO movie, TheatreResponseVO theatre, String time) {
		this.movie = movie;
		this.theatre = theatre;
		this.time = time;
	}

	public MovieResponseVO getMovie() {
		return movie;
	}

	public void setMovie(MovieResponseVO movie) {
		this.movie = movie;
	}

	public TheatreResponseVO getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreResponseVO theatre) {
		this.theatre = theatre;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static ScreenResponseVO convertRequestToResponse(ScreenRequestVO requestVO) {
		MovieResponseVO movie = MovieResponseVO.convertRequestToResponse(requestVO.getMovie());
		TheatreResponseVO theatre = TheatreResponseVO.convertRequestToResponse(requestVO.getTheatre());
		return new ScreenResponseVO(movie, theatre, requestVO.getShowTiming());
	}

}
