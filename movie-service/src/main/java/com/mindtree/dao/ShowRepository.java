package com.mindtree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Show;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	
	
}
