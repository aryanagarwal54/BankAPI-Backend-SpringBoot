package com.example.BankServiceDemo.Model;

import org.springframework.stereotype.Component;

@Component
public class Transaction {
    private int userId;
    private String fromAccNo;
    private String toAccNo;
    private double amount;

    public Transaction() {
        super();
    }
    public Transaction(int userId, String fromAccNo, String toAccNo, double amount) {
        this.userId = userId;
        this.fromAccNo = fromAccNo;
        this.toAccNo = toAccNo;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFromAccNo() {
        return fromAccNo;
    }

    public void setFromAccNo(String fromAccNo) {
        this.fromAccNo = fromAccNo;
    }

    public String getToAccNo() {
        return toAccNo;
    }

    public void setToAccNo(String toAccNo) {
        this.toAccNo = toAccNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
