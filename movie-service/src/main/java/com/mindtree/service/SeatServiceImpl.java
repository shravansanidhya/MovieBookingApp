package com.mindtree.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.dao.SeatDao;
import com.mindtree.exception.DatabaseException;
import com.mindtree.exception.NoRecordException;
import com.mindtree.exception.RecordAlreadyExistsException;
import com.mindtree.exception.RecordNotModified;
import com.mindtree.model.Seat;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.SeatResponseVo;
import com.mindtree.vo.SeatUpdateVo;
import com.mindtree.vomappers.VoMapper;

/**
 * @author M1050831
 * 
 *         The Class SeatServiceImpl.
 *
 */
@Service
@Transactional
public class SeatServiceImpl implements SeatService {
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

	/** The seat repository */
	@Autowired
	private SeatDao seatDao;

	/**
	 * Updates seat status to 'Booked'
	 * 
	 * @return updated seat
	 * @throws NoRecordException
	 * @throws DatabaseException
	 * @throws RecordAlreadyExistsException
	 */
	@Override
	public ResponseObject<List<SeatResponseVo>> updateSeatStatus(SeatUpdateVo seatUpdateVo)
			throws DatabaseException, NoRecordException, RecordNotModified, RecordAlreadyExistsException {
		logger.trace("-> entering updateSeatStatus: ", seatUpdateVo);
		List<Seat> seatList = new ArrayList<>();
		ResponseObject<List<SeatResponseVo>> responseObject = new ResponseObject<List<SeatResponseVo>>();
		try {
//			Predicate<String> emailFilter = Pattern.compile("^[A-Z0-9]$").asPredicate();
//			if (!seatUpdateVo.getSeatNumbers().stream().allMatch(emailFilter)) {
//				throw new ConstraintViolationException(null);
//			}

			seatList = seatDao.getMultipleSeats(seatUpdateVo.getSeatNumbers(), seatUpdateVo.getShowId());

			if (seatUpdateVo.getSeatNumbers().size() != seatList.size()) {
				throw new SQLException();
			}

			else if (!seatList.stream().filter(seat -> seat.getSeatStatus().equals("Booked"))
					.collect(Collectors.toList()).isEmpty()) {
				throw new SQLException();
			}

			seatList.forEach(seat1 -> {
				seat1.setSeatStatus("Booked");
			});

			List<Seat> seatResponseList = seatDao.saveAll(seatList);

			responseObject.setData(VoMapper.convertSeatToSeatVo(seatResponseList));
			responseObject.setStatusCode(200);
			responseObject.setUserMessage("Seat has been booked");

		} catch (JDBCConnectionException | QueryTimeoutException ce) {
			throw new DatabaseException("Connection Timeout", ce);
		} catch (SQLException sqle) {
			throw new RecordNotModified("Seat not present", sqle);
		} catch (ConstraintViolationException cve) {
			throw new RecordAlreadyExistsException(cve);
		}
		logger.trace("<- exiting deleteShow: {} ", responseObject);

		return responseObject;
	}

}
