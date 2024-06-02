package com.example.BankServiceDemo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.example.BankServiceDemo.Model.*;
import com.example.BankServiceDemo.RabbitMqConfig.UserIdDTO;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankServiceDemo.Exception.BankAccountNotFound;
import com.example.BankServiceDemo.Exception.InsufficientBalance;
import com.example.BankServiceDemo.Exception.UserIdNotFound;
import com.example.BankServiceDemo.Exception.UserIdOrBankAccountNotFound;
import com.example.BankServiceDemo.Repository.bankRepo;

@Service
public class bankService implements iBankService{
	@Autowired
	private bankRepo repo;
	@Autowired
	private creditHistory cHistory;
	@Autowired
	private debitHistory dHistory;
	@Autowired
	private RabbitTemplate rt;
	@Autowired
	private DirectExchange exchange;
	public bankService() {
		super();
	}

	@Autowired
	public bankService(bankRepo repo, creditHistory cHistory, debitHistory dHistory, RabbitTemplate rt, DirectExchange exchange) {
		super();
		this.repo = repo;
		this.cHistory = cHistory;
		this.dHistory = dHistory;
		this.exchange = exchange;
		this.rt = rt;
	}

	public Bank createBankAcc(Bank b)
	{
		return repo.save(b);
	}

	public List<Bank> getBankDetailsByUserId(int userId) throws UserIdNotFound
	{
		if(repo.getBankDetailsByUserId(userId).isEmpty())
		{
			throw new UserIdNotFound();
		}
		return repo.getBankDetailsByUserId(userId);
	}
	
	public Bank getBankDetailsByUserIdAndAccNo(int userId, String accNo) throws UserIdNotFound, BankAccountNotFound
	{
		if(repo.getBankDetailsByUserId(userId).isEmpty())
		{
			throw new UserIdNotFound();
		}
		if(repo.getBankDetailsByAccNo(accNo) == null)
		{
			throw new BankAccountNotFound();
		}
		return repo.getBankDetailsByUserIdAndAccNo(userId, accNo);
	}
	
	public void makeTransaction(Transaction t) throws UserIdOrBankAccountNotFound, InsufficientBalance
	{
		//Updating the balance and credit history of the bank from which amount has been transferred
		Bank creditHistoryBank = repo.getBankDetailsByUserIdAndAccNo(t.getUserId(),t.getFromAccNo());
		Bank debitHistoryBank = repo.getBankDetailsByUserIdAndAccNo(t.getUserId(), t.getToAccNo());
		if(creditHistoryBank == null || debitHistoryBank == null)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		if(creditHistoryBank.getBalance()<t.getAmount())
		{
			throw new InsufficientBalance();
		}
		double remainingBalance = creditHistoryBank.getBalance()-t.getAmount();
		String fromBankName = creditHistoryBank.getBankName();
		String toBankName = debitHistoryBank.getBankName();
		LinkedList<creditHistory> chl = creditHistoryBank.getCh();
		if(chl == null)
		{
			chl = new LinkedList<creditHistory>();
		}
		Random rand = new Random();
		String transactionId = String.valueOf(rand.nextInt(15000000 - 10000000 + 1) + 10000000);
		String dateAndTime = LocalDateTime.now().toString();
		cHistory.setUserId(t.getUserId());
		cHistory.setAmount(t.getAmount());
		cHistory.setTransactionId(transactionId);
		cHistory.setToBankName(toBankName);
		cHistory.setTimeAndDate(dateAndTime);
		chl.addFirst(cHistory);
		creditHistoryBank.setBalance(remainingBalance);
		creditHistoryBank.setCh(chl);
		repo.save(creditHistoryBank);
		
		//Updating the balance and debit history of the bank to which amount has been transferred
		double updatedBalance = debitHistoryBank.getBalance() + t.getAmount();
		LinkedList<debitHistory> dhl = debitHistoryBank.getDh();
		if(dhl == null)
		{
			dhl = new LinkedList<debitHistory>();
		}
		dHistory.setUserId(t.getUserId());
		dHistory.setTransactionId(transactionId);
		dHistory.setAmount(t.getAmount());
		dHistory.setTimeAndDate(dateAndTime);
		dHistory.setFromBankName(fromBankName);
		dhl.addFirst(dHistory);
		debitHistoryBank.setBalance(updatedBalance);
		debitHistoryBank.setDh(dhl);
		repo.save(debitHistoryBank);
	}
	public Card addCard(int userId, String accNo, Card c) throws UserIdOrBankAccountNotFound
	{
		Bank existingBank = repo.getBankDetailsByUserIdAndAccNo(userId, accNo);
		if(existingBank == null)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		List<Card> cl = existingBank.getCard();
		if(cl == null)
		{
			cl = new ArrayList<Card>();
		}
		cl.add(c);
		existingBank.setCard(cl);
		repo.save(existingBank);
		return c;
	}
	
	public List<Card> getCardDetails(int userId, String accNo) throws UserIdOrBankAccountNotFound
	{
		Bank existingBank = repo.getBankDetailsByUserIdAndAccNo(userId, accNo);
		if(existingBank == null)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		return existingBank.getCard();
	}
	
	public double getBankBalance(int userId, String accNo) throws UserIdOrBankAccountNotFound
	{
		Bank existingBank = repo.getBankDetailsByUserIdAndAccNo(userId, accNo);
		if(existingBank == null)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		return existingBank.getBalance();
	}
	
	public List<creditHistory> getCreditHistory(int userId, String accNo) throws UserIdOrBankAccountNotFound
	{
		Bank existingBank = repo.getBankDetailsByUserIdAndAccNo(userId, accNo);
		if(existingBank == null)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		return existingBank.getCh();
	}
	
	public List<debitHistory> getDebitHistory(int userId, String accNo) throws UserIdOrBankAccountNotFound
	{
		Bank existingBank = repo.getBankDetailsByUserIdAndAccNo(userId, accNo);
		if(existingBank == null)
		{
			throw new UserIdOrBankAccountNotFound();
		}
		return existingBank.getDh();
	}

	public void sendUserIdToUserManagementService(int userId)
	{
		JSONObject data = new JSONObject();
		data.put("UserId1",userId);
		UserIdDTO udto = new UserIdDTO();
		udto.setJsonObject(data);
		rt.convertAndSend(exchange.getName(), "userId_route1",udto);
	}
	int userIdFromUserService;
	@RabbitListener(queues = "userId_queue2")
	public void getUserIdFromUserManagementService(UserIdDTO udto)
	{
		String userId = udto.getJsonObject().get("UserId2").toString();
		int id = Integer.valueOf(userId);
		userIdFromUserService = id;
	}

	public int getUserIdFromUserService()
	{
		return userIdFromUserService;
	}
}
