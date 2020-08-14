package com.mindtree.booking.vo;

public class MovieRequestVO {

	private long movieId;
	private String movieTitle;
	private float rating;
	private String releaseDate;
	private String directorName;
	private String producer;
	private String genre;
	private String language;
	private String imageURL;

	public MovieRequestVO() {
	}

	public MovieRequestVO(long movieId, String movieTitle, float rating, String releaseDate, String directorName,
			String producer, String genre, String language, String imageURL) {
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.directorName = directorName;
		this.producer = producer;
		this.genre = genre;
		this.language = language;
		this.imageURL = imageURL;
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
