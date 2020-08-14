package com.mindtree.vomappers;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.mindtree.model.Movie;
import com.mindtree.model.Seat;
import com.mindtree.model.Show;
import com.mindtree.model.Theatre;
import com.mindtree.vo.MovieRequestVo;
import com.mindtree.vo.MovieResponseVo;
import com.mindtree.vo.SeatResponseVo;
import com.mindtree.vo.ShowResponseVo;
import com.mindtree.vo.TheatreResponseVo;

@Component
public class VoMapper {
	private static ModelMapper modelMapper = new ModelMapper();

	public static Movie convertResponseToMovie(MovieRequestVo vo) {

		return modelMapper.map(vo, Movie.class);
	}

	public static MovieResponseVo convertResponseVoToRequestVo(MovieRequestVo vo) {

		return modelMapper.map(vo, MovieResponseVo.class);
	}

	public static List<MovieResponseVo> convertModelToVo(List<Movie> movie) {
		Type listType = new TypeToken<List<MovieResponseVo>>() {
		}.getType();
		return modelMapper.map(movie, listType);
	}

	public static MovieResponseVo convertModelToVo(Movie movie) {
		return modelMapper.map(movie, MovieResponseVo.class);
	}

	public static MovieResponseVo convertModelToResponseVo(Movie movie) {
		// TODO Auto-generated method stub
		return modelMapper.map(movie, MovieResponseVo.class);
	}

	public static SeatResponseVo convertSeatToSeatVo(Seat seat) {
		return modelMapper.map(seat, SeatResponseVo.class);
	}

	public static List<SeatResponseVo> convertSeatToSeatVo(List<Seat> seat) {
		Type listType = new TypeToken<List<SeatResponseVo>>() {
		}.getType();
		return modelMapper.map(seat, listType);
	}

	public static ShowResponseVo convertShowToShowResponseVo(Show show) {
		return modelMapper.map(show, ShowResponseVo.class);
	}

	public static ShowResponseVo map(Show show) {
		ShowResponseVo showResponseVo = modelMapper.map(show, ShowResponseVo.class);
		showResponseVo.setSeats(VoMapper.convertSeatToSeatVo(show.getSeatList()));

		return showResponseVo;
	}

	public static List<ShowResponseVo> mapAsList(List<Show> shows) {
		List<ShowResponseVo> showResponseVoList = modelMapper.map(shows, new TypeToken<List<ShowResponseVo>>() {
		}.getType());

		showResponseVoList.forEach(vo -> {

			Show show = shows.stream().filter(s -> s.getShowId() == vo.getId()).collect(Collectors.toList()).get(0);

			vo.setSeats(VoMapper.convertSeatToSeatVo(show.getSeatList()));

		});

		return showResponseVoList;
	}

	public static TheatreResponseVo convertTheatreToResponseVo(Theatre theatre) {
		return modelMapper.map(theatre, TheatreResponseVo.class);
	}

	public static List<TheatreResponseVo> convertTheatreListToResponseVo(List<Theatre> theatres) {

		return modelMapper.map(theatres, new TypeToken<List<TheatreResponseVo>>() {
		}.getType());

	}
}
