package com.mindtree.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mindtree.dao.ShowRepository;
import com.mindtree.exception.ShowNotFoundException;
import com.mindtree.model.Movie;
import com.mindtree.model.Seat;
import com.mindtree.model.Show;
import com.mindtree.model.Theatre;
import com.mindtree.uility.SeatProvider;
import com.mindtree.vo.AddShowRequest;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.ShowResponseVo;
import com.mindtree.vomappers.VoMapper;

/**
 * @author M1044311
 * 
 *         The Class ShowServiceImpl.
 *
 */
@Service
@Transactional
public class ShowServiceImpl implements ShowService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ShowServiceImpl.class);

	/** The show repository. */
	@Autowired
	private ShowRepository showRepository;

	/**
	 * Gets all shows
	 * 
	 * @return show list
	 * @throws ShowNotFoundException
	 */
	@Override
	public ResponseObject<List<ShowResponseVo>> getAllShows() throws ShowNotFoundException {
		logger.trace("-> entering getAllShows: ");
		List<Show> shows = showRepository.findAll();
		if (CollectionUtils.isEmpty(shows)) {
			throw new ShowNotFoundException("no shows exist");
		}
		ResponseObject<List<ShowResponseVo>> response = new ResponseObject<>();
		response.setData(VoMapper.mapAsList(shows));

		response.setStatusCode(200);
		response.setUserMessage("retrieved successfully");
		logger.trace("<- exiting getAllShows:{} ", response);
		return response;
	}

	/**
	 * Deletes show
	 * 
	 * @param showId
	 * @return string
	 * @throws ShowNotFoundException
	 */
	@Override
	public ResponseObject<ShowResponseVo> deleteShow(long showId) throws ShowNotFoundException {
		logger.trace("-> entering deleteShow: {} ", showId);
		Optional<Show> show = showRepository.findById(showId);
		if (!show.isPresent()) {
			throw new ShowNotFoundException("show not found");
		}
		showRepository.deleteById(showId);
		ResponseObject<ShowResponseVo> response = new ResponseObject<>();
		response.setStatusCode(200);
		response.setUserMessage("deleted successfully");
		logger.trace("<- exiting deleteShow: {} ", response);
		return response;
	}

	/**
	 * Gets show by show id
	 * 
	 * @param showId
	 * @return show
	 * @throws ShowNotFoundException
	 */
	@Override
	public ResponseObject<ShowResponseVo> getByShowId(long showId) throws ShowNotFoundException {
		logger.trace("-> entering getByShowId: ");
		ResponseObject<ShowResponseVo> showResponse = new ResponseObject<>();
		Show show = showRepository.findById(showId).get();
		showResponse.setData(VoMapper.map(show));

		showResponse.setStatusCode(200);
		showResponse.setUserMessage("retrieved successfully");
		logger.trace("<- exiting getByShowId:{} ", showResponse);
		return showResponse;
	}

	/**
	 * Adds show
	 * 
	 * @param show
	 * @return show
	 */
	@Override
	public ResponseObject<ShowResponseVo> addShow(AddShowRequest addShowRequest) {
		logger.trace("-> entering addShow: {} ", addShowRequest);
		ResponseObject<ShowResponseVo> showResponse = new ResponseObject<>();
		Show show = new Show();

		Movie movie = new Movie();
		movie.setMovieId(addShowRequest.getMovieId());

		Theatre theatre = new Theatre();
		theatre.setId(addShowRequest.getTheatreId());

		show.setMovie(movie);
		show.setTheatre(theatre);
		show.setShowTiming(addShowRequest.getShowTiming());

		List<Seat> seatList = SeatProvider.generateSeatList(show);
//		show = showRepository.save(show);
		show.setSeatList(seatList);
		show = showRepository.save(show);
		showResponse.setData(VoMapper.map(show));
		showResponse.setStatusCode(200);
		showResponse.setUserMessage("added successfully");
		logger.trace("<- entering addShow: {} ", showResponse);
		return showResponse;
	}

}
