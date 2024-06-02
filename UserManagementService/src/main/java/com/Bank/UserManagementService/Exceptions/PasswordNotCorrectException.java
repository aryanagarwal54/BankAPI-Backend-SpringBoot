package com.Bank.UserManagementService.Exceptions;

public class PasswordNotCorrectException extends RuntimeException{
	public PasswordNotCorrectException(String message)
	{
		super(message);
	}

}
