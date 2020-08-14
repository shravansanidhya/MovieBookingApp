package com.mindtree.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddShowRequest {
	
	@NotNull
	private Long movieId;
	
	@NotNull
	private Long theatreId;
	
	@NotNull
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private String showTiming;
	
}
