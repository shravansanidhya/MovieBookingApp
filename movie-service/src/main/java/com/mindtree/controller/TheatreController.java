package com.mindtree.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import com.mindtree.exception.RecordNotCreatedException;
import com.mindtree.exception.RecordNotFoundException;
import com.mindtree.exception.TheatreServiceException;
import com.mindtree.service.TheatreService;
import com.mindtree.vo.TheatreRequestVo;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.TheatreResponseVo;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);
	
	@Autowired
	private TheatreService TheatreService;
	
	@PostMapping("/add")
	public ResponseObject<TheatreResponseVo> addTheatre(@NotNull @Valid @RequestBody TheatreRequestVo request) throws RecordNotCreatedException {
		logger.trace("-> entering addTheatre: {} ",request);
		return this.TheatreService.addTheatre(request);
	}
	
	@GetMapping("/get/{id}")
	public ResponseObject<TheatreResponseVo> getTheatreById(@PathVariable int id) throws TheatreServiceException, RecordNotFoundException {
		logger.trace("-> entering getTheatreById: {} ",id);
		return this.TheatreService.getTheatreById(id);
		
	}
	
	@GetMapping("/getAll")
	public ResponseObject<List<TheatreResponseVo>> getAllTheatres() throws TheatreServiceException {
		logger.trace("-> entering getAllTheatres: ");
		return this.TheatreService.getAllTheatres();
		
	}
	
}
