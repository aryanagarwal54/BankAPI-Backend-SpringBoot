package com.example.BankServiceDemo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST , reason = "Bank Account not found")
public class BankAccountNotFound extends Exception{

	private static final long serialVersionUID = 1L;

}
