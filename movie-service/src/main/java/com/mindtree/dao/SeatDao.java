package com.mindtree.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Seat;

@Repository
public interface SeatDao extends JpaRepository<Seat, Object> {

	@Query("FROM Seat WHERE seatNumber IN :seatNumbers AND show.showId = :showId")
	List<Seat> getMultipleSeats(List<String> seatNumbers, long showId);
}
