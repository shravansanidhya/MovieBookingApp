package com.mindtree.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseVo {
	private int seatId;

	private String seatNumber;

	private String seatClass;

	private String seatStatus;
	

}
