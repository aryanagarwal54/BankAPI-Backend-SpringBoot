package com.Bank.UserManagementService.Service;

import java.util.List;
import java.util.Map;

import com.Bank.UserManagementService.Model.User;
import com.Bank.UserManagementService.RabbitMqConfig.UserIdDTO;

public interface IUserService {
	
	public User addUser(User u);
	public List<User> getAllUsers();
	public User getUserById(int id);
//	public User getUserByIdAndPassword(int userId, String password);
	public Map<String, String> getToken(int userId, String password);// for login
	public User updateUserById(int id, User user);
	public void getUserIdFromBankService(UserIdDTO udto);
	public void sendUserIdToBankService(User u);
}
