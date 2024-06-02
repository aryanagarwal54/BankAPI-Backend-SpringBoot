package com.example.BankServiceDemo.Model;

import org.springframework.stereotype.Component;

@Component
public class debitHistory {
	private int userId;
	private String transactionId;
	private double amount;
	private String timeAndDate;
	private String fromBankName;
	public debitHistory() {
		super();
	}
	public debitHistory(int userId, String transactionId, double amount, String timeAndDate, String fromBankName) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.timeAndDate = timeAndDate;
		this.fromBankName = fromBankName;
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
	public String getFromBankName() {
		return fromBankName;
	}
	public void setFromBankName(String fromBankName) {
		this.fromBankName = fromBankName;
	}
	
	
}
