package com.mindtree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "theatre_id")
	private long id;

	@Column(name = "theatre_name")
	private String theatreName;

	@Column(name = "theatre_location")
	private String theatreLocation;

	@Column(name = "theatre_address")
	private String theatreAddress;

}
