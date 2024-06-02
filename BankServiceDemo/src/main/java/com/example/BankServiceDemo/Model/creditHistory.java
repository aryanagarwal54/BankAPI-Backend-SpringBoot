package com.example.BankServiceDemo.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class creditHistory {
	private int userId;
	private String transactionId;
	private double amount;
	private String timeAndDate;
	private String toBankName;
	
	public creditHistory() {
		super();
	}
	public creditHistory(int userId, String transactionId, double amount, String timeAndDate, String toBankName) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.timeAndDate = timeAndDate;
		this.toBankName = toBankName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTimeAndDate() {
		return timeAndDate;
	}

	public void setTimeAndDate(String timeAndDate) {
		this.timeAndDate = timeAndDate;
	}

	public String getToBankName() {
		return toBankName;
	}

	public void setToBankName(String toBankName) {
		this.toBankName = toBankName;
	}
	
	

}
