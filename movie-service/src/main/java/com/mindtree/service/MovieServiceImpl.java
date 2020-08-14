package com.mindtree.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.dao.MovieDao;
import com.mindtree.exception.AddMovieException;
import com.mindtree.exception.DatabaseException;
import com.mindtree.exception.GetMovieException;
import com.mindtree.exception.MovieException;
import com.mindtree.exception.RecordAlreadyExistsException;
import com.mindtree.exception.RecordNotModified;
import com.mindtree.model.Movie;
import com.mindtree.vo.MovieRequestVo;
import com.mindtree.vo.MovieResponseVo;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vomappers.VoMapper;

/**
 * @author M1050831
 * 
 *         The Class MovieServiceImpl.
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	/** The movie repository */
	@Autowired
	private MovieDao movieDao;

	/**
	 * Adds movies
	 * 
	 * @return added movie
	 * @throws RecordAlreadyExistsException
	 * @throws AddMovieException
	 * @throws DatabaseException
	 */
	@Override
	public ResponseObject<MovieResponseVo> addMovie(MovieRequestVo movieRequestVo)
			throws RecordAlreadyExistsException, AddMovieException, DatabaseException {
		logger.trace("-> entering addMovie: {} ", movieRequestVo);
		ResponseObject<MovieResponseVo> responseObject = new ResponseObject<MovieResponseVo>();
		MovieResponseVo movieResponseVo = new MovieResponseVo();
		try {
			if (movieDao.findByMovieTitle(movieRequestVo.getMovieTitle()) != null) {
				throw new MovieException();
			}
			movieRequestVo.setMovieId(0);
			movieDao.saveAndFlush(VoMapper.convertResponseToMovie(movieRequestVo));
			movieResponseVo = VoMapper
					.convertModelToResponseVo(movieDao.findByMovieTitle(movieRequestVo.getMovieTitle()));
			responseObject.setData(movieResponseVo);
			responseObject.setUserMessage("added");
			responseObject.setStatusCode(200);
		} catch (MovieException me) {
			throw new RecordAlreadyExistsException("Record already present", me);
		} catch (JDBCConnectionException | QueryTimeoutException ce) {
			throw new DatabaseException("Database connection timeout", ce);
		} catch (Exception e) {
			throw new AddMovieException("Movie with name " + movieRequestVo.getMovieTitle() +" could not be added", e);
		}
		logger.trace("<- exiting addMovie: {} ", responseObject);
		return responseObject;
	}

	/**
	 * Gets movie by movie id
	 * 
	 * @return movie
	 * @throws GetMovieException
	 * @throws DatabaseException
	 */
	@Override
	public ResponseObject<MovieResponseVo> getMovieById(long movieid) throws GetMovieException, DatabaseException {
		logger.trace("-> entering getMovieById: {} ", movieid);
		MovieResponseVo movieResponseVo = new MovieResponseVo();
		ResponseObject<MovieResponseVo> responseObject = new ResponseObject<MovieResponseVo>();
		Movie movie = new Movie();
		try {
			movie = movieDao.getOne(movieid);
			if (movie.getMovieTitle() == null)
				throw new EntityNotFoundException();
			movieResponseVo = VoMapper.convertModelToResponseVo(movie);
			responseObject.setData(movieResponseVo);
			responseObject.setUserMessage("retrieved");
			responseObject.setStatusCode(200);
		} catch (EntityNotFoundException e) {
			throw new GetMovieException("Movie with id " + movieid +" could not be retrieved", e);
		} catch (JDBCConnectionException | QueryTimeoutException ce) {
			throw new DatabaseException("Connection timeout", ce);
		}
		logger.trace("<- exiting getMovieById: {} ", responseObject);
		return responseObject;
	}

	/**
	 * Gets all shows
	 * 
	 * @return movie list
	 * @throws GetMovieException
	 * @throws DatabaseException
	 * 
	 */
	@Override
	public ResponseObject<List<MovieResponseVo>> getAllMovies() throws GetMovieException, DatabaseException {
		logger.trace("-> entering getAllMovies: ");
		List<MovieResponseVo> movieResponseVoList = new ArrayList<MovieResponseVo>();
		ResponseObject<List<MovieResponseVo>> responseObject = new ResponseObject<List<MovieResponseVo>>();
		try {
			movieResponseVoList = VoMapper.convertModelToVo(movieDao.findAll());
			responseObject.setData(movieResponseVoList);
			responseObject.setUserMessage("retrieved successfully");
			responseObject.setStatusCode(200);
		} catch (EntityNotFoundException e) {
			throw new GetMovieException("Movie could not be retrieved", e);
		} catch (JDBCConnectionException | QueryTimeoutException ce) {
			throw new DatabaseException("Connection timeout", ce);
		}
		logger.trace("<- exiting getAllMovies: {} ", responseObject);
		return responseObject;
	}

	/**
	 * Deletes movie by movie id
	 * 
	 * @return String
	 * @throws DeleteMovieException
	 * @throws DatabaseException
	 */
	@Override
	public ResponseObject<MovieResponseVo> deleteMovie(long movieid) throws RecordNotModified, DatabaseException {
		logger.trace("-> entering deleteMovie: {} ", movieid);
		MovieResponseVo movieResponseVo;
		ResponseObject<MovieResponseVo> responseObject = new ResponseObject<MovieResponseVo>();
		try {
			if (!movieDao.findById(movieid).isPresent())
				throw new EntityNotFoundException();
			movieResponseVo = VoMapper.convertModelToResponseVo(movieDao.getOne(movieid));
			responseObject.setData(movieResponseVo);
			responseObject.setStatusCode(200);
			responseObject.setUserMessage("deletion successful");
			movieDao.deleteById(movieid);

		} catch (EntityNotFoundException | EmptyResultDataAccessException e) {
			throw new RecordNotModified("Movie with id " + movieid +" could not be deleted", e);
		} catch (JDBCConnectionException | QueryTimeoutException ce) {
			throw new DatabaseException("Connection timeout", ce);
		}
		logger.trace("<- exiting deleteMovie: {} ", responseObject);
		return responseObject;
	}

}
