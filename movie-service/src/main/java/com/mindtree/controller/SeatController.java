package com.mindtree.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.exception.DatabaseException;
import com.mindtree.exception.NoRecordException;
import com.mindtree.exception.RecordAlreadyExistsException;
import com.mindtree.exception.RecordNotModified;
import com.mindtree.service.SeatService;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.SeatResponseVo;
import com.mindtree.vo.SeatUpdateVo;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/seat", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);

	/** The seat service */
	@Autowired
	SeatService seatService;

	/**
	 * Updates seat status as 'Booked'
	 * 
	 * @return ResponseObject<SeatResponseVo>
	 * @throws NoRecordException
	 * @throws DatabaseException
	 * @throws RecordNotModified 
	 * @throws RecordAlreadyExistsException 
	 */
	@PatchMapping("/updateStatus")
	public ResponseObject<List<SeatResponseVo>> updateSeatStatus(@RequestBody SeatUpdateVo seatUpdateVo)
			throws DatabaseException, NoRecordException, RecordNotModified, RecordAlreadyExistsException {
		logger.trace("-> entering updateSeatStatus: ");
		System.out.println(seatUpdateVo);
		System.out.println("reached Here");
		return seatService.updateSeatStatus(seatUpdateVo);

	}

}
