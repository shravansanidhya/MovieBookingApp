package com.mindtree.service;

import java.util.List;

import com.mindtree.exception.ShowNotFoundException;
import com.mindtree.vo.AddShowRequest;
import com.mindtree.vo.ResponseObject;
import com.mindtree.vo.ShowResponseVo;

public interface ShowService {

	public ResponseObject<List<ShowResponseVo>> getAllShows() throws ShowNotFoundException;

	public ResponseObject<ShowResponseVo> getByShowId(long showId) throws ShowNotFoundException;

	public ResponseObject<ShowResponseVo> addShow(AddShowRequest show);

	public ResponseObject<ShowResponseVo> deleteShow(long showId) throws ShowNotFoundException;

}
