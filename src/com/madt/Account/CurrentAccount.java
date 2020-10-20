package com.madt.Account;

import com.madt.ClientDetails.Client;

public class CurrentAccount extends Account {

	public CurrentAccount(long account_number, double amount,String account_type,Client c) {
		super(account_number, amount,account_type,c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void deposit(double amount) {
		super.deposit(amount);
	}
	public void withdraw(double amount) {
		super.withdraw(amount);
	}
	@Override
	public void transfer() {
		// TODO Auto-generated method stub
		
	}

}
