package com.mindtree.service;



import java.util.List;

import com.mindtree.exception.RecordNotCreatedException;
import com.mindtree.exception.RecordNotFoundException;
import com.mindtree.exception.TheatreServiceException;
import com.mindtree.vo.GenericVo;
import com.mindtree.vo.TheatreRequestVo;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.TheatreResponseVo;

public interface TheatreService {

	public ResponseObject<TheatreResponseVo> addTheatre(TheatreRequestVo theatre) throws RecordNotCreatedException;
	
	public ResponseObject<TheatreResponseVo> getTheatreById(long id) throws TheatreServiceException, RecordNotFoundException;
	
	public ResponseObject<List<TheatreResponseVo>> getAllTheatres() throws TheatreServiceException;
	
	public GenericVo createErrorResponse(String message, String statusCode, String errorDescription,
			String errorMessage);
}
