package com.mindtree.vo;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class TheatreResponseVo {

	@Id
	private long theatreId;
	
	@NotNull
	private String theatreName;
	
	@NotNull
	private String theatreLocation;
	
	@NotNull
	private String theatreAddress;
	
	
}
