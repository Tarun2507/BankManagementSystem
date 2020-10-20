package com.madt.main;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.madt.Account.Account;

public class ClientStore implements Serializable {

	Map<Integer,List<Account>> clientStore;

	public Map<Integer, List<Account>> getClientStore() {
		return clientStore;
	}

	public void setClientStore(Map<Integer, List<Account>> clientStore) {
		this.clientStore = clientStore;
	}

	@Override
	public String toString() {
		return "ClientStore [clientStore=" + clientStore + "]";
	}


}
