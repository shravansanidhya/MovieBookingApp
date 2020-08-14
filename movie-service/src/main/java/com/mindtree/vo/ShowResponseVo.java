package com.mindtree.vo;

import java.util.List;

import com.mindtree.model.Movie;
import com.mindtree.model.Theatre;

import lombok.Data;

@Data
public class ShowResponseVo {

	private long id;

	private Movie movie;

	private Theatre theatre;

	private String showTiming;

	private List<SeatResponseVo> seats;
	
}
