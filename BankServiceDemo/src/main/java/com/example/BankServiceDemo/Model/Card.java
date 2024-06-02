package com.example.BankServiceDemo.Model;

import org.springframework.stereotype.Component;

@Component
public class Card {
	private String cardType;
	private String cardNo;
	private int cvv;
	private String expiryDate;
	
	
	public Card() {
		super();
	}


	public Card(String cardType, String cardNo, int cvv, String expiryDate) {
		super();
		this.cardType = cardType;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public int getCvv() {
		return cvv;
	}


	public void setCvv(int cvv) {
		this.cvv = cvv;
	}


	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	

}
