package com.mindtree.service;

import java.util.List;

import com.mindtree.exception.GenericException;
import com.mindtree.vo.MovieRequestVo;
import com.mindtree.vo.MovieResponseVo;
import com.mindtree.vo.ResponseObject;

public interface MovieService {

	public ResponseObject<MovieResponseVo> addMovie(MovieRequestVo movieRequestVo) throws GenericException;

	public ResponseObject<MovieResponseVo> getMovieById(long id) throws GenericException;

	public ResponseObject<List<MovieResponseVo>> getAllMovies() throws GenericException;

	public ResponseObject<MovieResponseVo> deleteMovie(long id) throws GenericException;

}