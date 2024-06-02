package com.Bank.UserManagementService;

import com.Bank.UserManagementService.Exceptions.IdNotFoundException;
import com.Bank.UserManagementService.Exceptions.PasswordMismatchException;
import com.Bank.UserManagementService.Model.User;
import com.Bank.UserManagementService.Repository.UserRepository;
import com.Bank.UserManagementService.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ServiceLayerTesting {
	UserRepository repo = mock(UserRepository.class);
	@InjectMocks
	private UserService userService;
	User u;

	@BeforeEach
	public void SetUp() {
		u=new User(123,"Rahul","rahul@itc.com","85828323","12345","12345");
	}
	@AfterEach
	public void tearDown() {
		u = null;
	}
	@Test
	public void testAddUser()
	{
		User user1=new User();
		user1.setId(1123);
		user1.setUserName("Rahul");
		user1.setPassword("rashul@itc");
		user1.setEmail("rahul@gmail.com");
		user1.setConfPassword("rashul@itc");
		user1.setPhoneNumber("85756575757");
		when(repo.save(Mockito.any(User.class))).thenReturn(user1);
		User savedUser=userService.addUser(user1);
		assertNotNull(savedUser);
		assertEquals(user1.getId(),savedUser.getId());
		assertEquals(u.getUserName(),savedUser.getUserName());
	}
	@Test
	public void testGetAllUsers(){
		when(repo.findAll()).thenReturn(Arrays.asList(new User(),new User()));
		List<User> userList=userService.getAllUsers();
		assertNotNull(userList);
		assertEquals(2,userList.size());
	}

	@Test
	public void testGetUserById()
	{
		when(repo.findById(u.getId())).thenReturn(u);
		User foundUser= userService.getUserById(u.getId());
		assertNotNull(foundUser);
		assertEquals(u.getId(),foundUser.getId());
	}
	@Test
	public void testUpdateUserById_UserExists() {
		when(repo.findById(u.getId())).thenReturn(u);
		User updatedUser = new User(123, "Updated John", "updated@example.com","1234", "newPassword", "newPassword");
		User u1 =userService.updateUserById(updatedUser.getId(), updatedUser);
		assertEquals(123, updatedUser.getId());
		assertEquals(u1.getUserName(), updatedUser.getUserName());
		assertEquals(u1.getEmail(), updatedUser.getEmail());
	}
}