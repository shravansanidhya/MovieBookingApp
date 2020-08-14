package com.mindtree.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="shows")
public class Show {

	@Id
//	@Column(name = "showid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long showId;

	@OneToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "movie_id" , referencedColumnName = "movieId")
	private Movie movie;
	
	@OneToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "theatre_id" , referencedColumnName = "theatreId")
	private Theatre theatre;

	@Column(name = "show_timing")
	private String showTiming;
	
	@OneToMany(mappedBy="show", cascade = CascadeType.ALL)
	private List<Seat> seatList;

}
