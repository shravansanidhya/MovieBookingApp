package com.mindtree.booking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mindtree.booking.entity.Booking;
import com.mindtree.booking.exception.BookingNotFoundException;
import com.mindtree.booking.service.BookingService;
import com.mindtree.booking.vo.BookingRequestVO;
import com.mindtree.booking.vo.BookingResponseVO;
import com.mindtree.booking.vo.ResponseObject;
import com.mindtree.booking.vo.ScreenRequestVO;
import com.mindtree.booking.vo.ScreenResponseVO;
import com.mindtree.booking.vo.SeatRequestBodyVO;
import com.mindtree.booking.vo.SeatRequestVO;
import com.mindtree.booking.vo.UserRequestVO;

/**
 * @author M1049008
 *
 */
@RestController
@CrossOrigin
@RequestMapping("api/v1/bookingManagement/")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private Environment env;
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	@GetMapping(value = "/getBookings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject<List<BookingResponseVO>> getBookingById(@PathVariable("id") long userId)
			throws BookingNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Long> userUriVariables = new HashMap<>();
		userUriVariables.put("userId", userId);
		String userBaseUrl = env.getProperty("user-service.base-url");
		String getUserById = env.getProperty("user-service.get-user-by-id");
		ResponseObject<UserRequestVO> userRequest = restTemplate.exchange(userBaseUrl + getUserById, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseObject<UserRequestVO>>() {
				}, userUriVariables).getBody();
		if (userRequest.getError() != null) {
			return new ResponseObject<List<BookingResponseVO>>(HttpStatus.NOT_FOUND.value(), "User Does Not Exists.",
					null, userRequest.getError());
		}
		List<BookingResponseVO> userBookingsResponse = new ArrayList<>();
		List<Booking> userBookingList = bookingService.getBookingsById(userId);
		String movieBaseUrl = env.getProperty("movie-service.show.base-url");
		String showById = env.getProperty("movie-service.get.show-by-id");
		for (Booking booking : userBookingList) {
			Map<String, Long> uriVariables = new HashMap<>();
			uriVariables.put("showId", booking.getScreeningId());
			ResponseObject<ScreenRequestVO> screenRequest = restTemplate.exchange(movieBaseUrl + showById,
					HttpMethod.GET, null, new ParameterizedTypeReference<ResponseObject<ScreenRequestVO>>() {
					}, uriVariables).getBody();

			if (screenRequest.getData() != null) {
				BookingResponseVO userBookingResponse = new BookingResponseVO(booking.getId(),
						ScreenResponseVO.convertRequestToResponse(screenRequest.getData()), booking.getBookingDate(),
						booking.getBookingTime(), booking.getBookedSeats(), booking.getTotalAmount(),
						booking.getMode());
				userBookingResponse.setMode(booking.getMode());
				userBookingsResponse.add(userBookingResponse);
			} else {
				LOGGER.error(screenRequest.getError().getErrorMessage());
				return new ResponseObject<List<BookingResponseVO>>(screenRequest.getStatusCode(),
						screenRequest.getUserMessage(), null, screenRequest.getError());
			}
		}
		return new ResponseObject<List<BookingResponseVO>>(HttpStatus.FOUND.value(), "Booking Details Retireved",
				userBookingsResponse, null);
	}

	/**
	 * @param bookingVO
	 * @return
	 */
	@PostMapping(value = "/addBooking", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject<BookingResponseVO> createBooking(@Valid @RequestBody BookingRequestVO bookingVO) {
		RestTemplate restTemplate = new RestTemplate();
		Booking booking = new Booking(bookingVO.getUserId(), bookingVO.getScreeningId(), bookingVO.getTheatreId(),
				bookingVO.getMovieId(), bookingVO.getBookingDate(), bookingVO.getBookingTime(),
				bookingVO.getBookedSeats(), bookingVO.getTotalAmount(), bookingVO.getMode());
		String movieBaseUrl = env.getProperty("movie-service.show.base-url");
		String showById = env.getProperty("movie-service.get.show-by-id");

		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("showId", booking.getScreeningId());
		ResponseObject<ScreenRequestVO> screenRequest = restTemplate.exchange(movieBaseUrl + showById, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseObject<ScreenRequestVO>>() {
				}, uriVariables).getBody();
		if (screenRequest.getData() == null) {
			LOGGER.error("Show does not exists!");
			return new ResponseObject<BookingResponseVO>(HttpStatus.NOT_FOUND.value(), screenRequest.getUserMessage(),
					null, screenRequest.getError());
		} else {
			LOGGER.info("Show exists!");
			String seatBaseUrl = env.getProperty("movie-service.seat.base-url");
			String updateStatus = env.getProperty("movie-service.update-seat-status");
			HttpHeaders headers = new HttpHeaders();
			MediaType mediaType = new MediaType("application", "json");
			headers.setContentType(mediaType);
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(requestFactory);
			HttpEntity<SeatRequestBodyVO> requestEntity = new HttpEntity<SeatRequestBodyVO>(
					new SeatRequestBodyVO(bookingVO.getScreeningId(), bookingVO.getBookedSeats()), headers);
			ResponseObject<List<SeatRequestVO>> seatRequest = restTemplate
					.exchange(seatBaseUrl + updateStatus, HttpMethod.PATCH, requestEntity,
							new ParameterizedTypeReference<ResponseObject<List<SeatRequestVO>>>() {
							})
					.getBody();

			if (seatRequest.getError() != null) {
				LOGGER.error(seatRequest.getError().getErrorMessage());
				return new ResponseObject<BookingResponseVO>(HttpStatus.CONFLICT.value(), seatRequest.getUserMessage(),
						null, seatRequest.getError());
			} else {
				LOGGER.info("Seat Status Updated Successfully!");

				booking = bookingService.saveBooking(booking);

				LOGGER.info("Booking Saved Successfully!");

				BookingResponseVO userBookingResponse = new BookingResponseVO();
				userBookingResponse.setId(booking.getId());
				userBookingResponse.setScreening(ScreenResponseVO.convertRequestToResponse(screenRequest.getData()));
				userBookingResponse.setBookingDate(booking.getBookingDate());
				userBookingResponse.setBookingTime(booking.getBookingTime());
				List<String> seatNumbers = seatRequest.getData().stream().map(seat -> seat.getSeatNumber())
						.collect(Collectors.toList());
				userBookingResponse.setBookedSeats(seatNumbers);
				userBookingResponse.setTotalAmount(booking.getTotalAmount());
				userBookingResponse.setMode(booking.getMode());

				return new ResponseObject<BookingResponseVO>(HttpStatus.FOUND.value(), "Booking Details Retireved",
						userBookingResponse, null);
			}
		}

	}

//	@SuppressWarnings("unchecked")
//	@GetMapping("getUser/{emailId}")
//	public ResponseObject<UserRequestVO> getUser(@PathVariable String emailId)
//			throws JsonParseException, JsonMappingException, IOException {
//		String userBaseUrl = env.getProperty("user-service.base-url");
//		String getById = env.getProperty("user-service.get-user-by-id");
//		Map<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("email", emailId);
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseObject<UserRequestVO> userRequest = restTemplate.exchange(userBaseUrl + getById, HttpMethod.GET, null,
//				new ParameterizedTypeReference<ResponseObject<UserRequestVO>>() {
//				}, uriVariables).getBody();
//		if (userRequest.getError() != null) {
//			LOGGER.error(userRequest.getError().getDescription());
//			return new ResponseObject<UserRequestVO>(HttpStatus.NOT_FOUND.value(), "User doesn't exists!", null,
//					userRequest.getError());
//		} else {
//			LOGGER.info("User Details Retireved!");
//			return new ResponseObject<UserRequestVO>(HttpStatus.FOUND.value(), "User Details Retireved!",
//					userRequest.getData(), null);
//		}
//	}

	@GetMapping("test")
	public String test() {
		return "Booking Service is up and running";
	}

	@GetMapping("postTest")
	public String testPost() {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);
		HttpEntity<String> requestEntity = new HttpEntity<String>("Text");
		return restTemplate
				.exchange("http://localhost:8081/api/v1/user/postTest", HttpMethod.PATCH, requestEntity, String.class)
				.getBody();
	}
}
