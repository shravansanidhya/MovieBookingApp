package com.mindtree.user.service;

import java.util.List;

import com.mindtree.user.entity.User;
import com.mindtree.user.exception.DuplicateExcepiton;
import com.mindtree.user.exception.NotFoundException;
import com.mindtree.user.exception.NoUsersException;

/**
 * @author M1049008
 *
 */
public interface UserService {
	
	public User save(User user) throws DuplicateExcepiton;
	
	public User update(User user) throws NotFoundException;
	
	public void delete(long userId) throws NotFoundException;
	
	public User findByEmail(String emailId) throws NotFoundException;
	
	public User findByMobileNo(String mobileNo) throws NotFoundException;
	
	public User findById(long userId) throws NotFoundException;
	
	public List<User> findAll() throws NoUsersException;

}
