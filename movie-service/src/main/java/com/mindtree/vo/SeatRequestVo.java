package com.mindtree.vo;

import com.mindtree.model.Show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequestVo {
	private int seatId;

	private String seatNumber;

	private String seatClass;

	private String seatStatus;

	private Show show;
}
