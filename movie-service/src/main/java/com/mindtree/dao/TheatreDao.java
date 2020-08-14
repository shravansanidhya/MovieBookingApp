package com.mindtree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Theatre;

@Repository
public interface TheatreDao extends JpaRepository<Theatre, Integer> {

	public Theatre findTheatreById(long id);

}
