package com.mindtree.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class TheatreRequestVo {
	
	@NotNull
	private String theatreName;
	
	@NotNull
	private String theatreLocation;
	
	@NotNull
	private String theatreAddress;
	
	
}
