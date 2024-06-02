package com.example.BankServiceDemo.Service;

import com.example.BankServiceDemo.Exception.BankAccountNotFound;
import com.example.BankServiceDemo.Exception.InsufficientBalance;
import com.example.BankServiceDemo.Exception.UserIdNotFound;
import com.example.BankServiceDemo.Exception.UserIdOrBankAccountNotFound;
import com.example.BankServiceDemo.Model.*;
import com.example.BankServiceDemo.RabbitMqConfig.UserIdDTO;

import java.util.List;

public interface iBankService {
	public Bank createBankAcc(Bank b);
	public List<Bank> getBankDetailsByUserId(int userId) throws UserIdNotFound;
	public Bank getBankDetailsByUserIdAndAccNo(int userId, String accNo) throws UserIdNotFound, BankAccountNotFound;
	public void makeTransaction(Transaction t) throws UserIdOrBankAccountNotFound, InsufficientBalance;
	public Card addCard(int userId, String accNo, Card c) throws UserIdOrBankAccountNotFound;
	public List<Card> getCardDetails(int userId, String accNo) throws UserIdOrBankAccountNotFound;
	public double getBankBalance(int userId, String accNo) throws UserIdOrBankAccountNotFound;
	public List<creditHistory> getCreditHistory(int userId, String accNo) throws UserIdOrBankAccountNotFound;
	public List<debitHistory> getDebitHistory(int userId, String accNo) throws UserIdOrBankAccountNotFound;
	public void getUserIdFromUserManagementService(UserIdDTO udto);
	public int getUserIdFromUserService();


}
