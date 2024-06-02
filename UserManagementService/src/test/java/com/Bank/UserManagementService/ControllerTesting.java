package com.Bank.UserManagementService;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.Bank.UserManagementService.Controller.UserController;
import com.Bank.UserManagementService.Model.User;
import com.Bank.UserManagementService.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ControllerTesting {
	UserService userservice = mock(UserService.class);

	@InjectMocks
	private UserController ucon;

	@Autowired
	private MockMvc mockmvc;

	private User user;
	List<User> userL;


	@BeforeEach
	void setUp()
	{
		user=new User(77, "vijay", "abc12@mail.com", "990320987", "hi$67", "hi$67");
		mockmvc=MockMvcBuilders.standaloneSetup(ucon).build();
		userL=new ArrayList<User>();
		userL.add(user);
	}

	@AfterEach
	void tearDown() {
		user=null;
	}

	@Test
	@DisplayName("Test to register user")
	void givenUserSavedReturnSameUser() throws Exception
	{
		when(userservice.addUser(user)).thenReturn(user);
		mockmvc.perform(post("/BankApi/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk());
	}


	public String jsonToString(User u) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		String jsonContent=obj.writeValueAsString(u);
		return jsonContent;

	}

	@Test
	@DisplayName("Test get all Users")
	void shouldFetchAllUsers() throws Exception
	{
		when(userservice.getAllUsers()).thenReturn(userL);
		this.mockmvc.perform(get("/BankApi/get-all-users"))
				.andExpect(status().isOk());
	}


	@Test
	@DisplayName("Test get user by id")
	void shouldFetchUserById() throws Exception {
		when(userservice.getUserById(user.getId())).thenReturn(user);
		this.mockmvc.perform(get("/BankApi/user/77"))
				.andExpect(status().isOk());

	}

	@Test
	@DisplayName("Test update user")
	void shouldUpdateUser() throws Exception
	{
		when(userservice.addUser(user)).thenReturn(user);
		mockmvc.perform(put("/BankApi/update-user/77")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonToString(user)))
				.andExpect(status().isOk());
	}
}