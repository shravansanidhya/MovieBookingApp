package com.mindtree.user.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.user.UserServiceApplication;
import com.mindtree.user.repository.UserRepository;
import com.mindtree.user.service.serviceImpl.UserServiceImpl;
import com.mindtree.user.vo.UserRequestVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Mock
	private UserRepository userRepo;
	@InjectMocks
	private UserServiceImpl userService;

	static UserRequestVO userRequest = new UserRequestVO();

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@BeforeClass
	public static void setupRequest() {
		userRequest.setEmailId("jaiswal.amitabh751@gmail.com");
		userRequest.setMobileNo("8948943884");
		userRequest.setName("Amitabh Jaiswal");
		userRequest.setPassword("root123");
	}

	@Test
	public void testAddUser() throws JsonProcessingException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/user/registerUser").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(userRequest)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.error").doesNotExist()).andExpect(jsonPath("$.data").exists()).andDo(print());
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/getAllUsers").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.error").doesNotExist()).andExpect(jsonPath("$.data").exists()).andDo(print());
	}

	@Test
	public void testGetUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/getUser/jaiswal.amitabh751@gmail.com")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$.error").doesNotExist())
				.andExpect(jsonPath("$.data").exists()).andDo(print());
	}

}
