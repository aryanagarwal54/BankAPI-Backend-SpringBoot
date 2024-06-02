package com.example.BankServiceDemo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST , reason = "User Id or Bank Account not found")
public class UserIdOrBankAccountNotFound extends Exception{

	private static final long serialVersionUID = 1L;

}
