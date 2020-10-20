package com.madt.ClientDetails;

import java.io.Serializable;

public class Client implements Serializable {

	private int client_id;
	private String full_name;
	private long phone_no;
	private String address;
	
	public Client(int client_id, String full_name, long phone_no, String address) {
		super();
		this.client_id = client_id;
		this.full_name = full_name;
		this.phone_no = phone_no;
		this.address = address;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public long getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
