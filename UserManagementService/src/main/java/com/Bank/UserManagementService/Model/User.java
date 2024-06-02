package com.Bank.UserManagementService.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id	
	private int id;
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private String confPassword;
    
	public int getId() { return id; }
	public String getUserName() {return userName;}
	public String getEmail() {return email; }
	public String getPhoneNumber() { return phoneNumber; }
	public String getPassword() { return password; }
	public String getConfPassword() { return confPassword; }

	public void setId(int id) {	this.id = id; }
	public void setUserName(String userName) { this.userName = userName; }
	public void setEmail(String email) { this.email = email; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public void setPassword(String password) { this.password = password; }
	public void setConfPassword(String confPassword) { this.confPassword = confPassword; }
	
	
	public User(int id, String userName, String email, String phoneNumber, String password, String confPassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.confPassword = confPassword;
	}
	public User() {	super(); }
		
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", confPassword=" + confPassword + "]";
	}
    
    
    
	
}