package com.Bank.UserManagementService.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Bank.UserManagementService.RabbitMqConfig.UserIdDTO;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bank.UserManagementService.Exceptions.IdNotFoundException;
import com.Bank.UserManagementService.Exceptions.PasswordMismatchException;
import com.Bank.UserManagementService.Exceptions.PasswordNotCorrectException;
import com.Bank.UserManagementService.Exceptions.UserAlreadyExistsException;
import com.Bank.UserManagementService.Model.User;
import com.Bank.UserManagementService.Repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository urepo;
	@Autowired
	private DirectExchange exchange;
	@Autowired
	private RabbitTemplate rt;
	public UserService()
	{
		super();
	}
	public UserService(UserRepository urepo, RabbitTemplate rt, DirectExchange exchange)
	{
		this.urepo = urepo;
		this.rt = rt;
		this.exchange = exchange;
	}
	@Override
	public User addUser(User u) {
		
		if(urepo.findById(u.getId())!= null)
			throw new UserAlreadyExistsException("User with this Id already Exists");
		else if(!(u.getPassword().equals(u.getConfPassword())))
			throw new PasswordMismatchException("User password and confirm password do not match");
		
		return urepo.save(u);
	}

	@Override
	public List<User> getAllUsers() {
		return urepo.findAll();
	}

	@Override
	public User getUserById(int userId) {
		if(urepo.findById(userId)==null)
			throw new IdNotFoundException("User with the ID: "+ userId +" not found");
		
		return urepo.findById(userId);
	}

	@Override
	public User updateUserById(int userId, User user){
		
		User u= urepo.findById(userId);
		if(u== null)
			throw new IdNotFoundException("User with the ID: "+ userId +" not found");
		else if(!(user.getPassword().equals(user.getConfPassword())))
			throw new PasswordMismatchException("User password and confirm password do not match");
		
		u.setUserName(user.getUserName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setConfPassword(user.getConfPassword());	
		
		urepo.save(u);
		return u;
	}

	public Map<String, String> getToken(int id, String password)
	{		
		if(urepo.findById(id)== null)
			throw new IdNotFoundException("User with the ID: "+ id +" not found");
		else if(!(urepo.findById(id).getPassword().equals(password)))
				throw new PasswordNotCorrectException("password is not correct");
		
		String jwtToken= Jwts.builder().setSubject(urepo.findById(id).getUserName())
				.setIssuedAt(new Date(0))
				.signWith(SignatureAlgorithm.HS256, "team4")
				.compact();
		
		Map<String, String> jToken= new HashMap<String ,String>();
		jToken.put("token", jwtToken);
		
		return jToken;
	}
	@RabbitListener(queues = "userId_queue1")
	public void getUserIdFromBankService(UserIdDTO udto)
	{
		String userId = udto.getJsonObject().get("UserId1").toString();
		int id = Integer.valueOf(userId);
		System.out.println(udto.getJsonObject().get("UserId1").toString());
		User u = urepo.findById(id);
		sendUserIdToBankService(u);
	}
	public void sendUserIdToBankService(User u)
	{
		JSONObject data = new JSONObject();
		if(u == null)
		{
			data.put("UserId2",0);
		}
		else {
			data.put("UserId2",1);
		}
		UserIdDTO udto = new UserIdDTO();
		udto.setJsonObject(data);
		rt.convertAndSend(exchange.getName(), "userId_route2",udto);
	}
}
