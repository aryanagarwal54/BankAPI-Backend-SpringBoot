package com.example.BankServiceDemo.Model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bank {
	private int userId;
	private String bankName;
	@Id
	private String accNo;
	private String ifsc;
	private String password;
	private double balance;
	private List<Card> card;
	private LinkedList<debitHistory> dh;
	private LinkedList<creditHistory> ch;
	
	public Bank() {
		super();
	}

	public Bank(int userId, String bankName, String accNo, String ifsc, String password, double balance, List<Card> card,
			LinkedList<debitHistory> dh, LinkedList<creditHistory> ch) {
		super();
		this.userId = userId;
		this.bankName = bankName;
		this.accNo = accNo;
		this.ifsc = ifsc;
		this.password = password;
		this.balance = balance;
		this.card = card;
		this.dh = dh;
		this.ch = ch;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getBankName()
	{
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Card> getCard() {
		return card;
	}
	public void setCard(List<Card> card) {
		this.card = card;
	}

	public LinkedList<debitHistory> getDh() {
		return dh;
	}
	public void setDh(LinkedList<debitHistory> dh) {
		this.dh = dh;
	}

	public LinkedList<creditHistory> getCh() {
		return ch;
	}
	public void setCh(LinkedList<creditHistory> ch) {
		this.ch = ch;
	}
}
