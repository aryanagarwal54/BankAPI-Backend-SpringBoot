package com.example.BankServiceDemo.Controller;

import com.example.BankServiceDemo.Exception.*;
import com.example.BankServiceDemo.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankServiceDemo.Model.Bank;
import com.example.BankServiceDemo.Model.Card;
import com.example.BankServiceDemo.Service.bankService;

@RestController
@RequestMapping("BankApi")
public class bankController {
	@Autowired
	private bankService service;
	
	
	public bankController() {
		super();
	}


	@PostMapping("createBankAccount")
	public ResponseEntity<?> createAcc(@RequestBody Bank b) throws UserNotRegistered, InterruptedException {
		service.sendUserIdToUserManagementService(b.getUserId());
		Thread.sleep(500);
		if(service.getUserIdFromUserService() ==0)
		{
			throw new UserNotRegistered();
		}
		return new ResponseEntity<>(service.createBankAcc(b), HttpStatus.CREATED);
	}

	@GetMapping("getAllBankDetails/{userId}")
	public ResponseEntity<?> fetchAllAcc(@PathVariable int userId) throws UserIdNotFound
	{
		try {
		    return new ResponseEntity<>(service.getBankDetailsByUserId(userId), HttpStatus.OK);
		}
		catch(UserIdNotFound unf)
		{
			throw new UserIdNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("getBankDetails/{userId}/{accNo}")
	public ResponseEntity<?> fetchAcc(@PathVariable int userId, @PathVariable String accNo) throws UserIdNotFound, BankAccountNotFound
	{
		try {
		     return new ResponseEntity<>(service.getBankDetailsByUserIdAndAccNo(userId, accNo),HttpStatus.OK);
		}
		catch(UserIdNotFound unf)
		{
			throw new UserIdNotFound();
		}
		catch(BankAccountNotFound bnf)
		{
			throw new BankAccountNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("transaction")
	public void makeTransaction(@RequestBody Transaction t) throws InsufficientBalance, UserIdOrBankAccountNotFound
	{
		try {
		    service.makeTransaction(t);
		}
		catch(UserIdOrBankAccountNotFound unf)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		catch(InsufficientBalance ib)
		{
			throw new InsufficientBalance();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@PostMapping("addCard/{userId}/{accNo}")
	public ResponseEntity<?> addCard(@PathVariable int userId, @PathVariable String accNo, @RequestBody Card c) throws UserIdOrBankAccountNotFound
	{
		try {
		    return new ResponseEntity<>(service.addCard(userId, accNo, c), HttpStatus.CREATED);
		}
		catch(UserIdOrBankAccountNotFound unf)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getCardDetails/{userId}/{accNo}")
	public ResponseEntity<?> fetchCardDetails(@PathVariable int userId, @PathVariable String accNo) throws UserIdOrBankAccountNotFound
	{
		try {
		   return new ResponseEntity<>(service.getCardDetails(userId,accNo), HttpStatus.OK);
		}
		catch(UserIdOrBankAccountNotFound unf)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getBalance/{userId}/{accNo}")
	public ResponseEntity<?> fetchBalance(@PathVariable int userId, @PathVariable String accNo) throws UserIdOrBankAccountNotFound
	{
		try {
		   return new ResponseEntity<>(service.getBankBalance(userId, accNo), HttpStatus.OK);
		}
		catch(UserIdOrBankAccountNotFound unf)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getCreditHistory/{userId}/{accNo}")
	public ResponseEntity<?> fetchCreditHistory(@PathVariable int userId, @PathVariable String accNo) throws UserIdOrBankAccountNotFound
	{
		try {
		   return new ResponseEntity<>(service.getCreditHistory(userId, accNo), HttpStatus.OK);
		}
		catch(UserIdOrBankAccountNotFound unf)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("getDebitHistory/{userId}/{accNo}")
	public ResponseEntity<?> fetchDebitHistory(@PathVariable int userId, @PathVariable String accNo) throws UserIdOrBankAccountNotFound
	{
		try {
		   return new ResponseEntity<>(service.getDebitHistory(userId, accNo), HttpStatus.OK);
		}
		catch(UserIdOrBankAccountNotFound unf)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}
