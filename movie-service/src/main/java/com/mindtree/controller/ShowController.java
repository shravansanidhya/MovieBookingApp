package com.mindtree.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.exception.ShowNotFoundException;
import com.mindtree.service.ShowService;
import com.mindtree.vo.AddShowRequest;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.ShowResponseVo;

/**
 * The Class ShowController.
 */
@RestController
@RequestMapping(value = "/show", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShowController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);

	/** The show service. */
	@Autowired
	private ShowService showService;

	/**
	 * Gets all shows
	 * 
	 * @return show list
	 * @throws ShowNotFoundException
	 */
	@GetMapping(value = "/getAllShows")
	public ResponseObject<List<ShowResponseVo>> getAllShows() throws ShowNotFoundException {
		logger.trace("-> entering getAllShows: ");
		return showService.getAllShows();
	}

	/**
	 * Gets show by show id
	 * 
	 * @param showId
	 * @return show
	 * @throws ShowNotFoundException
	 */
	@GetMapping(value = "/getByShowId/{showId}")
	public ResponseObject<ShowResponseVo> getByShowId(@PathVariable int showId) throws ShowNotFoundException {
		logger.trace("-> entering getByShowId: {} ", showId);
		return showService.getByShowId(showId);
	}

	/**
	 * Adds show
	 * 
	 * @param show
	 * @return show
	 */
	@PostMapping(value = "/addShow")
	public ResponseObject<ShowResponseVo> addShow(@NotNull @Valid @RequestBody AddShowRequest show) {
		logger.trace("-> entering addShow: {} ", show);
		return showService.addShow(show);
	}

	/**
	 * Deletes show
	 * 
	 * @param showId
	 * @return string
	 * @throws ShowNotFoundException
	 */
	@DeleteMapping(value = "/deleteShow/{showId}")
	public ResponseObject<ShowResponseVo> deleteShow(@PathVariable long showId) throws ShowNotFoundException {
		logger.trace("-> entering deleteShow: {} ", showId);
		return showService.deleteShow(showId);
	}

}
