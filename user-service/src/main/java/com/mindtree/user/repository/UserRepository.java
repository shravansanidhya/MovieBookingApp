package com.mindtree.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailId(String emailId);

	public User findByMobileNo(String mobileNo);

}
