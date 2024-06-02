package com.Bank.UserManagementService.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Bank.UserManagementService.Exceptions.IdNotFoundException;
import com.Bank.UserManagementService.Exceptions.PasswordMismatchException;
import com.Bank.UserManagementService.Exceptions.PasswordNotCorrectException;
import com.Bank.UserManagementService.Exceptions.UserAlreadyExistsException;
import com.Bank.UserManagementService.Model.User;
import com.Bank.UserManagementService.Service.UserService;

@RestController
@RequestMapping("BankApi")
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u)
	{
		try {
			uservice.addUser(u);
			return ResponseEntity.ok("User Registered Successfully");
		}
		catch(UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(PasswordMismatchException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/get-all-users")
	public ResponseEntity<?> getAllUsers()
	{
		return new ResponseEntity<>(uservice.getAllUsers(), HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id)
	{
		try {
			return new ResponseEntity<>(uservice.getUserById(id), HttpStatus.OK);			
		}
		catch(IdNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/login/{id}/{password}")
	public ResponseEntity<?> login(@PathVariable int id, @PathVariable String password)
	{
		try {
			return new ResponseEntity(uservice.getToken(id, password), HttpStatus.OK);
		}
		catch(IdNotFoundException | PasswordNotCorrectException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}
	
	@PutMapping("/update-user/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable int id, @RequestBody User user)
	{
		try {
			return new ResponseEntity<>(uservice.updateUserById(id, user), HttpStatus.OK);
		}
		catch(IdNotFoundException | PasswordMismatchException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
