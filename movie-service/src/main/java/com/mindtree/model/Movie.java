package com.mindtree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Movie {

	@Id
//	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long movieId;

	@Column(name = "movie_title")
	private String movieTitle;

	@Column(name = "rating")
	private double rating;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "director_name")
	private String directorName;

	@Column(name = "producer")
	private String producer;

	@Column(name = "genre")
	private String genre;

	@Column(name = "language")
	private String language;

	@Column(name = "movie_image_URL")
	private String movieImageURL;

}
