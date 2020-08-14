package com.mindtree.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.user.entity.User;
import com.mindtree.user.exception.DuplicateExcepiton;
import com.mindtree.user.exception.NoUsersException;
import com.mindtree.user.exception.NotFoundException;
import com.mindtree.user.service.UserService;
import com.mindtree.user.vo.ResponseObject;
import com.mindtree.user.vo.UserRequestVO;
import com.mindtree.user.vo.UserResponseVO;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user/")
public class UserController {

	@Autowired
	private UserService userService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value = "getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject<List<UserResponseVO>> getAllUsers() throws NoUsersException {

		List<UserResponseVO> usersResponse = new ArrayList<UserResponseVO>();
		List<User> users = userService.findAll();

		for (User user : users) {
			UserResponseVO tempUser = new UserResponseVO(user.getId(), user.getName(), user.getEmailId(),
					user.getMobileNo());
			usersResponse.add(tempUser);
		}

		LOGGER.info("All Users Fetched");
		return new ResponseObject<List<UserResponseVO>>(HttpStatus.FOUND.value(), "Users Fetched Successfully",
				usersResponse, null);

	}

	@GetMapping(value = "getUser/{email}")
	public ResponseObject<UserResponseVO> getUserById(@PathVariable String email) throws NotFoundException {

		User user = userService.findByEmail(email);

		LOGGER.info("Users with email " + user.getEmailId() + " Fetched");
		return new ResponseObject<UserResponseVO>(HttpStatus.FOUND.value(), "Users Fetched Successfully",
				new UserResponseVO(user.getId(), user.getName(), user.getEmailId(), user.getMobileNo()), null);

	}

	@PostMapping(value = "registerUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject<UserResponseVO> addUser(@Valid @RequestBody UserRequestVO userVO)
			throws DuplicateExcepiton, NotFoundException {

		User user = new User(userVO.getName(), userVO.getEmailId(), userVO.getMobileNo(), userVO.getPassword());

		userService.save(user);

		user = userService.findByEmail(user.getEmailId());

		LOGGER.info("User with email " + user.getEmailId() + " saved.");
		return new ResponseObject<UserResponseVO>(HttpStatus.CREATED.value(), "User Created Successfully",
				new UserResponseVO(user.getId(), user.getName(), user.getEmailId(), user.getMobileNo()), null);

	}

	@GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject<UserResponseVO> getUser(@PathVariable long userId) throws NotFoundException {

		UserResponseVO usersResponse = new UserResponseVO();
		User user = userService.findById(userId);

		usersResponse.setEmailId(user.getEmailId());
		usersResponse.setId(user.getId());
		usersResponse.setMobileNo(user.getMobileNo());
		usersResponse.setName(user.getName());

		LOGGER.info("User Fetched!");
		return new ResponseObject<UserResponseVO>(HttpStatus.FOUND.value(), "User Fetched Successfully", usersResponse,
				null);

	}

//	@GetMapping("testUserService")
//	public String test() {
//		return "User Service Up And Running";
//	}
//	
//	@PatchMapping("postTest")
//	public String postTest(@RequestBody String body) {
//		return body;
//	}

}
