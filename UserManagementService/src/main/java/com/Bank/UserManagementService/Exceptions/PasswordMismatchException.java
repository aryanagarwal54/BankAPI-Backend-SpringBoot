package com.Bank.UserManagementService.Exceptions;

public class PasswordMismatchException extends RuntimeException{
	public PasswordMismatchException(String message)
	{
		super(message);
	}
}
