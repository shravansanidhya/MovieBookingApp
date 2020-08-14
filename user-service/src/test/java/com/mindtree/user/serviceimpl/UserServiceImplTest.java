package com.mindtree.user.serviceimpl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mindtree.user.entity.User;
import com.mindtree.user.exception.DuplicateExcepiton;
import com.mindtree.user.exception.NoUsersException;
import com.mindtree.user.exception.NotFoundException;
import com.mindtree.user.repository.UserRepository;
import com.mindtree.user.service.serviceImpl.UserServiceImpl;

public class UserServiceImplTest {

	@Mock
	private UserRepository userRepo;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void positiveTestSave() throws DuplicateExcepiton {
		User user = new User();
		user.setId(7623);
		user.setEmailId("jaiswal.amitabh751@gmail.com");
		user.setMobileNo("8948943884");
		user.setName("Amitabh Jaiswal");
		user.setPassword("root123");

		Mockito.when(userRepo.findByMobileNo(user.getMobileNo())).thenReturn(null);
		Mockito.when(userRepo.findByEmailId(user.getEmailId())).thenReturn(null);
		Mockito.when(userRepo.save(user)).thenReturn(user);

		assertThat(userServiceImpl.save(user)).isEqualTo(user);
	}

	@Test(expected = DuplicateExcepiton.class)
	public void negativeMobileDuplicateTestSave() throws DuplicateExcepiton {

		User existingUser = new User();
		existingUser.setId(73);
		existingUser.setEmailId("xyz@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		User user = new User();
		user.setId(7623);
		user.setEmailId("jaiswal.amitabh751@gmail.com");
		user.setMobileNo("8948943884");
		user.setName("Amitabh Jaiswal");
		user.setPassword("root123");

		Mockito.when(userRepo.findByMobileNo(user.getMobileNo())).thenReturn(existingUser);

		assertThat(userServiceImpl.save(user).getMobileNo()).isEqualTo(user.getMobileNo());
	}

	@Test(expected = DuplicateExcepiton.class)
	public void negativeEmailDuplicateTestSave() throws DuplicateExcepiton {

		User existingUser = new User();
		existingUser.setId(73);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		User user = new User();
		user.setId(7623);
		user.setEmailId("jaiswal.amitabh751@gmail.com");
		user.setMobileNo("8948943889");
		user.setName("Amitabh Jaiswal");
		user.setPassword("root123");

		Mockito.when(userRepo.findByMobileNo(user.getMobileNo())).thenReturn(null);

		Mockito.when(userRepo.findByEmailId(user.getEmailId())).thenReturn(existingUser);

		assertThat(userServiceImpl.save(user).getEmailId()).isEqualTo(user.getEmailId());
	}

	@Test
	public void positiveTestUpdate() throws NotFoundException {
		User existingUser = new User();
		existingUser.setId(7623);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		User user = new User();
		user.setId(7623);
		user.setEmailId("jaiswal.amitabh751@gmail.com");
		user.setMobileNo("8948943884");
		user.setName("Amitabh Jaiswal");
		user.setPassword("password");

		Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.of(existingUser));
		Mockito.when(userRepo.saveAndFlush(user)).thenReturn(user);

		assertThat(userServiceImpl.update(user)).isEqualTo(user);

	}

	@Test(expected = NotFoundException.class)
	public void negativeTestUpdate() throws NotFoundException {

		User user = new User();
		user.setId(7623);
		user.setEmailId("jaiswal.amitabh751@gmail.com");
		user.setMobileNo("8948943884");
		user.setName("Amitabh Jaiswal");
		user.setPassword("password");

		Mockito.when(userRepo.findById(user.getId())).thenThrow(NoSuchElementException.class);

		userServiceImpl.update(user);
	}

//	@Test
//	public void positiveTestDelete() throws NotFoundException {
//		User existingUser = new User();
//		existingUser.setId(7623);
//		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
//		existingUser.setMobileNo("8948943884");
//		existingUser.setName("Amitabh Jaiswal");
//		existingUser.setPassword("root123");
//		
//		Mockito.when(userRepo.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
//		
//		userServiceImpl.delete(7623);
//		
//		verify(UserRepository.class, times(1));
//		
//	}

	@Test
	public void positiveTestFindByEmail() throws NotFoundException {
		String email = "jaiswal.amitabh751@gmail.com";
		User existingUser = new User();
		existingUser.setId(7623);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		Mockito.when(userRepo.findByEmailId(email)).thenReturn(existingUser);

		assertThat(userServiceImpl.findByEmail(email)).isEqualTo(existingUser);
	}

	@Test(expected = NotFoundException.class)
	public void negativeTestFindByEmail() throws NotFoundException {
		String email = "jaiswal.amitabh751@gmail.com";
		User existingUser = new User();
		existingUser.setId(7623);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		Mockito.when(userRepo.findByEmailId(email)).thenReturn(null);

		userServiceImpl.findByEmail(email);
	}

	@Test
	public void positiveTestFindByMobileNo() throws NotFoundException {
		String mobileNumber = "8948943884";
		User existingUser = new User();
		existingUser.setId(7623);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		Mockito.when(userRepo.findByMobileNo(mobileNumber)).thenReturn(existingUser);

		assertThat(userServiceImpl.findByMobileNo(mobileNumber)).isEqualTo(existingUser);

	}

	@Test(expected = NotFoundException.class)
	public void negativeTestFindByMobileNo() throws NotFoundException {
		String mobileNumber = "8948943884";
		User existingUser = new User();
		existingUser.setId(7623);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		Mockito.when(userRepo.findByMobileNo(mobileNumber)).thenReturn(null);

		userServiceImpl.findByMobileNo(mobileNumber);

	}

	@Test
	public void positiveTestFindById() throws NotFoundException {
		long userId = 7623L;
		User existingUser = new User();
		existingUser.setId(7623);
		existingUser.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser.setMobileNo("8948943884");
		existingUser.setName("Amitabh Jaiswal");
		existingUser.setPassword("root123");

		Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(existingUser));

		assertThat(userServiceImpl.findById(userId)).isEqualTo(existingUser);
	}

	@Test(expected = NotFoundException.class)
	public void negativeTestFindById() throws NotFoundException {
		long userId = 7623L;

		Mockito.when(userRepo.findById(userId)).thenThrow(NoSuchElementException.class);

		userServiceImpl.findById(userId);
	}

	@Test
	public void positiveTestFindAll() throws NoUsersException {
		User existingUser1 = new User();
		existingUser1.setId(7623);
		existingUser1.setEmailId("jaiswal.amitabh751@gmail.com");
		existingUser1.setMobileNo("8948943884");
		existingUser1.setName("Amitabh Jaiswal");
		existingUser1.setPassword("root123");

		User existingUser2 = new User();
		existingUser2.setId(76232);
		existingUser2.setEmailId("xyz@gmail.com");
		existingUser2.setMobileNo("8948943889");
		existingUser2.setName("ABC XYZ");
		existingUser2.setPassword("password");

		List<User> allUsers = new ArrayList<>();

		allUsers.add(existingUser1);
		allUsers.add(existingUser2);

		Mockito.when(userRepo.findAll()).thenReturn(allUsers);

		assertThat(userServiceImpl.findAll()).isEqualTo(allUsers);
	}

	@Test(expected = NoUsersException.class)
	public void negativeTestFindAll() throws NoUsersException {

		List<User> allUsers = new ArrayList<>();

		Mockito.when(userRepo.findAll()).thenReturn(allUsers);

		userServiceImpl.findAll();
	}

}
