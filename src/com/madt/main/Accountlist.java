package com.madt.main;

import java.io.Serializable;
import java.util.List;

import com.madt.Account.Account;
import com.madt.ClientDetails.Client;

public class Accountlist implements Serializable {
	List<Account> accountList;
	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	
	
}
