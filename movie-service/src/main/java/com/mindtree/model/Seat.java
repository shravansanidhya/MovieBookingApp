package com.mindtree.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seat_id")
	private long seatId;

	@Column(name = "seat_number")
	private String seatNumber;

	@Column(name = "seat_class")
	private String seatClass;

	@Column(name = "seat_status")
	private String seatStatus;

	@ManyToOne(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
//	@JoinColumn(name = "show_id", referencedColumnName = "showId")
	private Show show;

}
