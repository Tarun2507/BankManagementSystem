package com.madt.Account;

import com.madt.ClientDetails.Client;

public class SavingsAccount extends Account {

	public SavingsAccount(long account_number, double amount,String account_type,Client c) {
		super(account_number, amount,account_type,c);
	}

	public void deposit() {
		super.deposit(amount);
	}
	public void withdraw() {
		super.withdraw(amount);
	}
	public long getAccountNumber() {
		return account_number;
	}
	@Override
	public void transfer() {
		// TODO Auto-generated method stub	
	}
	public void payUtilityBills() {
		super.payUtilityBills(amount);
	}
}
