package com.mindtree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, Object>{

	public Movie findByMovieTitle(String movieTitle);
	
//	public boolean existsByName(String movieName);
	}
