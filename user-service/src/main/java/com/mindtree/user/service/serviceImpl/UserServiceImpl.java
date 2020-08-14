package com.mindtree.user.service.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.user.entity.User;
import com.mindtree.user.exception.DuplicateExcepiton;
import com.mindtree.user.exception.NotFoundException;
import com.mindtree.user.exception.NoUsersException;
import com.mindtree.user.repository.UserRepository;
import com.mindtree.user.service.UserService;

/**
 * @author M1049008
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User save(User user) throws DuplicateExcepiton {
		User userByMobileNo = userRepo.findByMobileNo(user.getMobileNo());
		if (userByMobileNo != null) {
			LOGGER.error("Mobile Number {} already exists.", user.getMobileNo());
			throw new DuplicateExcepiton("Mobile Number " + user.getMobileNo() + " already exists!");
		}
		User userByEmailId = userRepo.findByEmailId(user.getEmailId());
		if (userByEmailId != null) {
			LOGGER.error("Email Id {} already exists.", user.getEmailId());
			throw new DuplicateExcepiton("Email ID " + user.getEmailId() + " already exists!");
		}
		return userRepo.save(user);
	}

	@Override
	public User update(User user) throws NotFoundException {
		try {
			User persistedUser = userRepo.findById(user.getId()).get();
			LOGGER.info("User with id " + user.getId() + " retireved!");
			BeanUtils.copyProperties(user, persistedUser);
			return userRepo.saveAndFlush(persistedUser);
		} catch (NoSuchElementException e) {
			LOGGER.error("User with id " + user.getId() + " does not exists!");
			throw new NotFoundException("User with id " + user.getId() + " does not exists!");
		}
	}

	@Override
	public void delete(long userId) throws NotFoundException {
		try {
			User persistedUser = userRepo.findById(userId).get();
			LOGGER.info("User with id " + userId + " retireved!");
			userRepo.delete(persistedUser);
		} catch (NoSuchElementException e) {
			LOGGER.error("User with id " + userId + " does not exists!");
			throw new NotFoundException("User with id \" + userId + \" does not exists!");
		}
	}

	@Override
	public User findByEmail(String emailId) throws NotFoundException {
		User persistedUser = userRepo.findByEmailId(emailId);
		if (persistedUser == null) {
			LOGGER.error("User with email " + emailId + " does not exists!");
			throw new NotFoundException("User with email " + emailId + " does not exists!");
		}
		LOGGER.info("User with email " + emailId + " retireved!");
		return persistedUser;
	}

	@Override
	public User findByMobileNo(String mobileNo) throws NotFoundException {
		User persistedUser = userRepo.findByMobileNo(mobileNo);
		if (persistedUser == null) {
			LOGGER.error("User with mobile number " + mobileNo + " does not exists!");
			throw new NotFoundException("User with mobile number " + mobileNo + " does not exists!");
		}
		LOGGER.info("User with mobile number " + mobileNo + " retireved!");
		return persistedUser;
	}

	@Override
	public User findById(long userId) throws NotFoundException {
		try {
			User userById = userRepo.findById(userId).get();
			LOGGER.info("User with id " + userId + " retireved!");
			return userById;
		} catch (NoSuchElementException e) {
			LOGGER.error("User with id " + userId + " does not exists!");
			throw new NotFoundException("User with id " + userId + " does not exists!");
		}
	}

	@Override
	public List<User> findAll() throws NoUsersException {
		List<User> allUsers = userRepo.findAll();
		if (allUsers.isEmpty()) {
			LOGGER.error("No User exists!");
			throw new NoUsersException("No User exists!");

		}
		LOGGER.info("Users Retireved!");
		return allUsers;
	}

}
