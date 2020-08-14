package com.mindtree.booking.vo;

public class MovieResponseVO {

	private long movieId;
	private String movieTitle;
	private String movieImageURL;

	public MovieResponseVO() {
	}

	public MovieResponseVO(long movieId, String movieTitle, String movieImageURL) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieImageURL = movieImageURL;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieImageURL() {
		return movieImageURL;
	}

	public void setMovieImageURL(String movieImageURL) {
		this.movieImageURL = movieImageURL;
	}

	public static MovieResponseVO convertRequestToResponse(MovieRequestVO requestVO) {
		return new MovieResponseVO(requestVO.getMovieId(), requestVO.getMovieTitle(), requestVO.getImageURL());
	}

}
