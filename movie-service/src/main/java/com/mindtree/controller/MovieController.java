package com.mindtree.controller;

import java.util.List;

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

import com.mindtree.exception.GenericException;
import com.mindtree.service.MovieService;
import com.mindtree.vo.MovieRequestVo;
import com.mindtree.vo.MovieResponseVo;
import com.mindtree.vo.ResponseObject;

@RestController
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	/** The movie service */
	@Autowired
	MovieService movieService;

	/**
	 * Adds movies
	 * 
	 * @return ResponseObject<MovieResponseVo>
	 * @throws GenericException
	 */
	@PostMapping("/addMovie")
	public ResponseObject<MovieResponseVo> addMovies(@RequestBody MovieRequestVo movieRequestVo)
			throws GenericException {
		logger.trace("-> entering addMovies: ");
		return movieService.addMovie(movieRequestVo);
	}

	/**
	 * Gets movie by movie id
	 * 
	 * @return ResponseObject<MovieResponseVo>
	 * @throws GenericException
	 */
	@GetMapping("/getMovie/{id}")
	public ResponseObject<MovieResponseVo> getMovieById(@PathVariable long id) throws GenericException {
		logger.trace("-> entering getMovieById: ");
		return movieService.getMovieById(id);
	}

	/**
	 * Gets all movies
	 * 
	 * @return ResponseObject<MovieResponseVo>
	 * @throws GenericException
	 */
	@GetMapping("/getMovies")
	public ResponseObject<List<MovieResponseVo>> getAllMovies() throws GenericException {
		logger.trace("-> entering getAllMovies: ");
		return movieService.getAllMovies();

	}

	/**
	 * deletes movies
	 * 
	 * @return ResponseObject<MovieResponseVo>
	 * @throws GenericException
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseObject<MovieResponseVo> deleteMovieById(@PathVariable long id) throws GenericException {
		logger.trace("-> entering deleteMovieById: ");
		return movieService.deleteMovie(id);

	}

	@GetMapping("testMovieService")
	public String test() {
		return "Movie Service Up And Running";
	}

}
