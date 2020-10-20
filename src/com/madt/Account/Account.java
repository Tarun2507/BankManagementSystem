package com.madt.Account;

import java.io.Serializable;

import com.madt.ClientDetails.Client;

public abstract class Account implements Serializable {
	long account_number;
	double amount;
	double balance;
	String account_type;
	Client client;

	public Account(long account_number, double amount,String account_type,Client c) {
		super();
		this.account_number = account_number;
		this.amount = amount;
		this.account_type = account_type;
		this.balance = this.amount;
		this.client = c;
	}
	
	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public long getAccount_number() {
		return account_number;
	}
	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void deposit(double amount) {
		this.balance = this.balance + amount;
	}
	public void withdraw(double amount) {
		this.balance = this.balance - amount;
	}
	
	public abstract void transfer();
	public void payUtilityBills(double amount) {
		this.balance = this.balance-amount;
	}
}
