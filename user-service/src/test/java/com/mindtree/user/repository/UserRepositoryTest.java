package com.mindtree.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.user.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	@Autowired
	UserRepository userRepo;
	User user = new User();

	@Before
	public void setUp() throws Exception {
//		User user = new User();
		user.setEmailId("jaiswal.amitabh751@gmail.com");
		user.setMobileNo("8948943884");
		user.setName("Amitabh Jaiswal");
		user.setPassword("root123");

		entityManager.persistAndFlush(user);
	}

	@Test
	public void saveTest() {

		assertThat(userRepo.findByEmailId(user.getEmailId())).isEqualTo(user);
	}

	@Test
	public void findByIdTest() {

		Long userId = (Long) entityManager.getId(user);

		assertThat(userRepo.findById(userId).get()).isEqualTo(user);
	}

	@Test
	public void findByMobileNoTest() {

		assertThat(userRepo.findByMobileNo(user.getMobileNo())).isEqualTo(user);
	}

	@Test
	public void findByEmailIdTest() {

		assertThat(userRepo.findByEmailId(user.getEmailId())).isEqualTo(user);
	}

	@Test
	public void findAllTest() {

		List<User> allUsers = new ArrayList<>();
		allUsers.add(user);

		assertThat(userRepo.findAll()).isEqualTo(allUsers);
	}

}
