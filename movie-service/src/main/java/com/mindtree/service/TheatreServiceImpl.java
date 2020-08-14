package com.mindtree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.TheatreDao;
import com.mindtree.exception.RecordNotCreatedException;
import com.mindtree.exception.RecordNotFoundException;
import com.mindtree.exception.TheatreServiceException;
import com.mindtree.model.Theatre;
import com.mindtree.service.TheatreService;
import com.mindtree.vo.GenericVo;
import com.mindtree.vo.TheatreRequestVo;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.TheatreResponseVo;
import com.mindtree.vomappers.VoMapper;


@Service
public class TheatreServiceImpl implements TheatreService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(TheatreServiceImpl.class);
	
	@Autowired
	private TheatreDao theatreDao;
	
	@Override
	public ResponseObject<TheatreResponseVo> addTheatre(TheatreRequestVo request) throws RecordNotCreatedException {
		logger.trace("-> entering addTheatre(): ", request);
		ResponseObject<TheatreResponseVo> responseObj = new ResponseObject<TheatreResponseVo>();
		
		try {		
		Theatre theatre = new Theatre();
		theatre.setTheatreName(request.getTheatreName());
		theatre.setTheatreLocation(request.getTheatreLocation());
		theatre.setTheatreAddress(request.getTheatreAddress());

		TheatreResponseVo responseVo = VoMapper.convertTheatreToResponseVo(this.theatreDao.save(theatre));
	    
		responseObj.setData(responseVo);
		responseObj.setUserMessage("Added Successfully");
		responseObj.setStatusCode(200);
	
		} catch (Exception e) {
			throw new RecordNotCreatedException("Unable to add theatre");
		}
		logger.trace("<- exiting addTheatre(): {} ", responseObj);
		return responseObj;
	
	}

	@Override
	public ResponseObject<TheatreResponseVo> getTheatreById(long id) throws TheatreServiceException, RecordNotFoundException {
		logger.trace("-> entering getTheatreById: {} ",id);
		ResponseObject<TheatreResponseVo> responseObj = new ResponseObject<TheatreResponseVo>(); 
		
		try{
		if(this.theatreDao.findTheatreById(id)!=null) {
			TheatreResponseVo responseVo = VoMapper.convertTheatreToResponseVo(this.theatreDao.findTheatreById(id));
			responseObj.setData(responseVo);
			responseObj.setUserMessage("Retrieved Successfully");
			responseObj.setStatusCode(200);
		} else {
			throw new TheatreServiceException();
		}
		} catch (TheatreServiceException e) {
			throw new RecordNotFoundException("unable to find theatre",e);
		} catch (Exception e) {
			throw new TheatreServiceException("Something went wrong in getting theatre");
		}
		logger.trace("<- exiting getTheatreById(): {} ", responseObj);
		return responseObj;
	}

	@Override
	public ResponseObject<List<TheatreResponseVo>> getAllTheatres() throws TheatreServiceException{
		logger.trace("-> entering getAllTheatres: ");
		ResponseObject<List<TheatreResponseVo>> responseObj = new ResponseObject<List<TheatreResponseVo>>();
		
		try {
			List<TheatreResponseVo> responseVo = VoMapper.convertTheatreListToResponseVo(this.theatreDao.findAll());

			responseObj.setData(responseVo);
			responseObj.setUserMessage("Retrieved Successfully");
			responseObj.setStatusCode(200);
			
		} catch(Exception e) {
			throw new TheatreServiceException("Something went wrong in fetching theatres",e);
		}
		logger.trace("<- exiting getAllTheatres(): {} ", responseObj);
		return responseObj;
	}
	
	public GenericVo createErrorResponse(String message, String statusCode, String errorDescription,
			String errorMessage) {
		GenericVo genericVo = new GenericVo();
		genericVo.setUserMessage(message);
		genericVo.setStatusCode(statusCode);
		genericVo.setErrorDescription(errorDescription);
		genericVo.setErrorMessage(errorMessage);
		
		return genericVo;

	}
	
}
