package com.mindtree.service;

import java.util.List;

import com.mindtree.exception.DatabaseException;
import com.mindtree.exception.NoRecordException;
import com.mindtree.exception.RecordAlreadyExistsException;
import com.mindtree.exception.RecordNotModified;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.SeatResponseVo;
import com.mindtree.vo.SeatUpdateVo;

public interface SeatService {

	public ResponseObject<List<SeatResponseVo>> updateSeatStatus(SeatUpdateVo seatUpdateVo)
			throws DatabaseException, NoRecordException, RecordNotModified,RecordAlreadyExistsException;
}
