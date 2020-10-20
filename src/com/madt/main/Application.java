package com.madt.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.madt.Account.Account;
import com.madt.Account.CurrentAccount;
import com.madt.Account.SavingsAccount;
import com.madt.ClientDetails.Client;

public class Application implements Serializable {
	private static Client c;
	private static Accountlist accountList;
	private static Map<Integer, List<Account>> client_map = new HashMap<Integer, List<Account>>();
	private static final String savings_account = "Savings";
	private static final String current_account = "Current";

	public static void main(String[] args) {
		int choice;
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("\t\t\t\t\t\t\t******** Welcome to Cannara Bank ********");
		System.out.println("\t\t\t\t\t\t\tPress 1 if you are an admin or Press 2 if you are a bank account holder");
		choice = sc.nextInt();
		if (choice == 1) {
			adminAccess();
		}
		if (choice == 2) {
			userAccess();
		}
	}

	public static void adminAccess() {
		viewUserDetails();
		Scanner sc = new Scanner(System.in);
		int admin_choice;
		while (true) {
			System.out.println("\t\t\t\t\t\t Hello Admin");
			System.out.println("Choose one among the following options");
			System.out.println(
					"1.Create\n2.Save\n3.View user details\n4.Edit details\n5.Perform client transactions\n6.Exit the application");
			System.out.println("Enter your choice");
			admin_choice = sc.nextInt();
			switch (admin_choice) {
			case 1:
				createAccount();
				break;
			case 2:
				saveDetailsIntoFile();
				break;
			case 3:
				printUserDetails();
				break;
			case 4:
				editDetails();
				break;
			case 5:
				performClientTransactions();
				break;
			case 6:
				System.out.println("Thank you come back soon");
				return;
			default:
				System.out.println("Invalid choice..");
				break;
			}
		}
	}

	private static void printUserDetails() {
		// TODO Auto-generated method stub
		viewUserDetails();
		System.out.println("\t\t\t\t\t\t\t****User details*******");
		for (Entry<Integer, List<Account>> m : client_map.entrySet()) {
			// System.out.println(m);
			List<Account> acc = m.getValue();
			for (int i = 0; i < acc.size(); i++) {
				System.out.println("ClientId:"+m.getKey());
				System.out.println("Name:"+acc.get(i).getClient().getFull_name());
				System.out.println("Phoneno:"+acc.get(i).getClient().getPhone_no());
				System.out.println("Address:"+acc.get(i).getClient().getAddress());
				System.out.println("Accountno:"+acc.get(i).getAccount_number());
				System.out.println("Accounttype:"+acc.get(i).getAccount_type());
				System.out.println("Balance:"+acc.get(i).getBalance());
				System.out.println("---other account details--");
				/*pw.print(m.getKey() + "=" + "Name :" + acc.get(i).getClient().getFull_name() + "," + "Phoneno:"
						+ acc.get(i).getClient().getPhone_no() + "," + "Address:"
						+ acc.get(i).getClient().getAddress() + "," + "Account number:"
						+ acc.get(i).getAccount_number() + "," + "Account type:" + acc.get(i).getAccount_type()
						+ "," + "Balance:" + acc.get(i).getBalance() + "*");*/
			}
			System.out.println("------------------------");
		}
	}

	private static Map<Integer, List<Account>> viewUserDetails() {
		// TODO Auto-generated method stub
		int client_id = 0;
		String name = null;
		long phoneno = 0L;
		String address = null;
		long account_number = 0L;
		String account_type = null;
		double balance = 0.0;
		Account acc = null;
		try {
			File toRead = new File("ClientDetails.txt");
			FileInputStream fis = new FileInputStream(toRead);
			Scanner sc = new Scanner(fis);
			// read data from file line by line:
			String currentLine;
			while (sc.hasNextLine()) {
				currentLine = sc.nextLine();
				//System.out.println(currentLine);
				StringTokenizer st = new StringTokenizer(currentLine, "*");
				List<Account> accountList = new ArrayList<Account>();
				while (st.hasMoreTokens()) {
					String list_item = st.nextToken();
					StringTokenizer st2 = new StringTokenizer(list_item, "=");
					client_id = Integer.parseInt(st2.nextToken());
					String client_details = st2.nextToken();
					StringTokenizer st3 = new StringTokenizer(client_details, ",");
					while (st3.hasMoreTokens()) {
						String field = st3.nextToken();
						//System.out.println(field);
						StringTokenizer st4 = new StringTokenizer(field, ":");
						String field_key =st4.nextToken();
						if (field_key.equalsIgnoreCase("name ")) {
							name = st4.nextToken();
							//System.out.println(name);
						}
						if (field_key.equalsIgnoreCase("Phoneno")) {
							
							phoneno = Long.parseLong(st4.nextToken());
						}
						if (field_key.equalsIgnoreCase("address")) {
							address = st4.nextToken();
						}
						if (field_key.equalsIgnoreCase("account number")) {
							account_number = Long.parseLong(st4.nextToken());
						}
						if (field_key.equalsIgnoreCase("Account type")) {
							account_type = st4.nextToken();
						}
						if (field_key.equalsIgnoreCase("balance")) {
							balance = Double.parseDouble(st4.nextToken());
						}
					}
					Client c = new Client(client_id, name, phoneno, address);
					//System.out.println(c.getPhone_no());
					if (account_type.equalsIgnoreCase(savings_account)) {
						acc = new SavingsAccount(account_number, balance, account_type, c);
					} if (account_type.equalsIgnoreCase(current_account)) {
						acc = new CurrentAccount(account_number, balance, account_type, c);
						System.out.println(acc);
					}
					accountList.add(acc);
					//System.out.println(accountList);
					client_map.put(c.getClient_id(),accountList);
				}

			}

			fis.close();

			// print All data in MAP
			/*
			 * for(Map.Entry<String,String> m :mapInFile.entrySet()) {
			 * System.out.println(m.getKey()+" : "+m.getValue()); }
			 */
		} catch (Exception e) {
		}
		//System.out.println(client_map);
		return null;
	}

	private static void performClientTransactions() {
		// TODO Auto-generated method stub
		viewUserDetails();
		accountList = new Accountlist();
		System.out.println("Enter unique id of the client");
		Scanner sc = new Scanner(System.in);
		int unique_id = sc.nextInt();
		//System.out.println((List<Account>) client_map.get(unique_id));
		List<Account> accList = (List<Account>) client_map.get(unique_id);
		//System.out.println(acc.getAccount_type());
	//	System.out.println(accList);
		accountList.setAccountList(accList);
		System.out.println("\t\t\t\t\t\t Hello ,Welcome back");
		while (true) {
			System.out.println("Choose from the following options");
			System.out.println(
					"1.Display balance\n2.Deposit money\n3.Withdraw\n4.Transfer b/w accounts\n5.Pay utility bill \n 6.Exit the menu");
			// Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				displayBalance(unique_id);
				break;
			case 2:
				depositMoney(unique_id);
				break;
			case 3:
				withdrawMoney(unique_id);
				break;
			case 4:
				transferMoneyBetweenAccounts(unique_id);
				break;
			case 5:
				payUtilityBills(unique_id);
				break;
			case 6:
				System.out.println("Getting back to user menu.");
				return;
			default:
				System.out.println("Invalid input");
				break;
			}
		}
	}

	private static void editDetails() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter unique id of the person which you want to edit");
		System.out.println("Note,you can edit only phone no due to security reasons");
		int unique_id = sc.nextInt();
		List<Account> accList = (List<Account>) client_map.get(unique_id);
		System.out.println("Enter type of account which you want to edit the details");
		String account_type = sc.next();
		for(int i =0;i<accList.size();i++) {
			if(account_type.equalsIgnoreCase(accList.get(i).getAccount_type())) {
				System.out.println("Enter the phoneno");
				long edited_phone_no = sc.nextLong();
				accList.get(i).getClient().setPhone_no(edited_phone_no);
				client_map.replace(unique_id,accList);
			}
		}
		
	}

	private static void saveDetailsIntoFile() {
		try {
			// File fileTwo=new File("Clients.txt");
			// FileOutputStream fos=new FileOutputStream(fileTwo);
			PrintWriter pw = new PrintWriter(new FileOutputStream("ClientDetails.txt", false));

			for (Entry<Integer, List<Account>> m : client_map.entrySet()) {
				// System.out.println(m);
				List<Account> acc = m.getValue();
				// System.out.println(acc.get(0).getAmount());
				// System.out.println(acc.get(0).getClient().getFull_name());
				for (int i = 0; i < acc.size(); i++) {
					pw.print(m.getKey() + "=" + "Name :" + acc.get(i).getClient().getFull_name() + "," + "Phoneno:"
							+ acc.get(i).getClient().getPhone_no() + "," + "Address:"
							+ acc.get(i).getClient().getAddress() + "," + "Account number:"
							+ acc.get(i).getAccount_number() + "," + "Account type:" + acc.get(i).getAccount_type()
							+ "," + "Balance:" + acc.get(i).getBalance() + "*");
				}
				pw.println();
			}

			pw.flush();
			pw.close();
			// fos.close();
		} catch (Exception e) {
		}
	}

	private static void createAccount() {
		viewUserDetails();
		List<Account> accountList = new ArrayList<Account>();
		Accountlist acc_List = new Accountlist();
		// TODO Auto-generated method stub
		while (true) {
			System.out.println(
					"Press 1 if you want to create new user or Press 2 if you want to create account for existing user and press 0,if you want to stop creating");
			System.out.println("Enter your choice");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			Account acc = null;
			if (choice == 1 || choice == 2) {
				// c = new Client();
				System.out.println("enter aadhar number or any unique id of the client");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.println("enter name of the client");
				String name = sc.nextLine();
				// sc.nextLine();
				//System.out.println(name);
				System.out.println("enter phone number of the client");
				long phone_no = sc.nextLong();
				sc.nextLine();
				System.out.println("enter address of the client");
				String address = sc.nextLine();
				c = new Client(id, name, phone_no, address);
				// sc.nextLine();
				System.out.println("Enter account type Savings/Current");
				String account_type = sc.next();
				System.out.println("Enter account number");
				long acc_num = sc.nextLong();
				System.out.println("Enter initial deposit amount");
				double amount = sc.nextDouble();
				if (account_type.equalsIgnoreCase(savings_account)) {
					acc = new SavingsAccount(acc_num, amount, savings_account, c);
				}
				if (account_type.equalsIgnoreCase(current_account)) {
					acc = new CurrentAccount(acc_num, amount, current_account, c);
				}
				//System.out.println(acc.toString());
				if (choice == 1) {
					accountList = new ArrayList<Account>();
					acc_List = new Accountlist();
				}

				accountList.add(acc);
				// acc_List.setAccountList(accountList);
				if (choice == 2) {
					//System.out.println(c.getClient_id());
					//System.out.println(accountList);
					client_map.replace(c.getClient_id(), accountList);
				} else {
					//System.out.println(c.getClient_id());
					//System.out.println(accountList);
					client_map.put(c.getClient_id(), accountList);
				}
			}
			if (choice == 0) {
				System.out.println("Returning back to main menu....");
				break;
			}
		}
		saveDetailsIntoFile();
	}

	/*
	 * private static void setClientStore(Client c2, List<Account> accList,Boolean
	 * alreadyExists) { //client_store = new ClientStore(); // client_map = new
	 * HashMap<Integer,List<Account>>(); if(alreadyExists) {
	 * client_map.put(c2.getClient_id(), accList);
	 * //System.out.println(accList.getAccountList());
	 * client_store.setClientStore(client_map); } }
	 */
	private static void userAccess() {
		viewUserDetails();
		accountList = new Accountlist();
		System.out.println("Enter your unique id");
		Scanner sc = new Scanner(System.in);
		int unique_id = sc.nextInt();
		System.out.println((List<Account>) client_map.get(unique_id));
		List<Account> accList = (List<Account>) client_map.get(unique_id);
		//System.out.println(acc.getAccount_type());
		//System.out.println(accList);
		accountList.setAccountList(accList);
		System.out.println("\t\t\t\t\t\t Hello ,Welcome back");
		while (true) {
			System.out.println("Choose from the following options");
			System.out.println(
					"1.Display balance\n2.Deposit money\n3.Withdraw\n4.Transfer b/w accounts\n5.Pay utility bill \n 6.Exit the menu");
			// Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				displayBalance(unique_id);
				break;
			case 2:
				depositMoney(unique_id);
				break;
			case 3:
				withdrawMoney(unique_id);
				break;
			case 4:
				transferMoneyBetweenAccounts(unique_id);
				break;
			case 5:
				payUtilityBills(unique_id);
				break;
			case 6:
				System.out.println("Getting back to user menu.");
				return;
			default:
				System.out.println("Invalid input");
				break;
			}
		}
	}

	private static void payUtilityBills(int unique_id) {
		// TODO Auto-generated method stub
		Account accc = null;
		int changed_index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter type of account you want to pay for utility bills");
		String account_type = sc.next();
		System.out.println("Enter amount you want to pay for the utility");
		double amount = sc.nextDouble();
		for (int i = 0; i < accountList.getAccountList().size(); i++) {
			if (account_type.equalsIgnoreCase(savings_account)) {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(savings_account)) {
					//System.out.println(accountList.getAccountList().get(i));
					accc = accountList.getAccountList().get(i);
					changed_index = i;
				}
			} else {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(current_account)) {
					accc = accountList.getAccountList().get(i);
					changed_index = i;
				}
			}
		}
		Account acc = accc;
		acc.payUtilityBills(amount);
		accountList.getAccountList().set(changed_index, acc);
		//System.out.println(acc.getBalance());
		//System.out.println(accountList.getAccountList());
		client_map.replace(unique_id, accountList.getAccountList());
		System.out.println("Payment processed successfully");
		//System.out.println(client_map.get(unique_id).get(0).getBalance());
	}

	private static void transferMoneyBetweenAccounts(int unique_id) {
		// TODO Auto-generated method stub
		Account acc_source = null;
		Account acc_destination = null;
		int changed_source_index = 0;
		int changed_destination_index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter type of account from which you want to transfer");
		String account_type = sc.next();
		System.out.println("Enter amount you want to pay for the utility");
		double amount = sc.nextDouble();
		System.out.println(accountList);
		for (int i = 0; i < accountList.getAccountList().size(); i++) {
			if (account_type.equalsIgnoreCase(savings_account)) {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(savings_account)) {
					//System.out.println(accountList.getAccountList().get(i));
					acc_source = accountList.getAccountList().get(i);
					changed_source_index = i;
				}
				else {
					acc_destination = accountList.getAccountList().get(i);
					changed_destination_index = i;
				}
			} else {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(current_account)) {
					acc_source = accountList.getAccountList().get(i);
					//System.out.println(acc_source);
					changed_source_index = i;
				}
				else {
					acc_destination = accountList.getAccountList().get(i);
					changed_destination_index = i;
				}
			}
		}
		acc_source.withdraw(amount);
		acc_destination.deposit(amount);
		accountList.getAccountList().set(changed_source_index, acc_source);
		accountList.getAccountList().set(changed_destination_index,acc_destination);
	//	System.out.println(acc.getBalance());
	//	System.out.println(accountList.getAccountList());
		client_map.replace(unique_id, accountList.getAccountList());
		System.out.println("Money has been transferred successfully");
	//	System.out.println(client_map.get(unique_id).get(0).getBalance());
	}

	private static void withdrawMoney(int unique_id) {
		// TODO Auto-generated method stub
		Account accc = null;
		int changed_index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter type of account from which you want to withdraw 1.Savings 2.Current");
		String account_type = sc.next();
		System.out.println("Enter amount you want to withdraw");
		double amount = sc.nextDouble();
		for (int i = 0; i < accountList.getAccountList().size(); i++) {
			if (account_type.equalsIgnoreCase(savings_account)) {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(savings_account)) {
					//System.out.println(accountList.getAccountList().get(i));
					accc = accountList.getAccountList().get(i);
					changed_index = i;
				}
			} else {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(current_account)) {
					accc = accountList.getAccountList().get(i);
					changed_index = i;
				}
			}
		}
		Account acc = accc;
		acc.withdraw(amount);
		accountList.getAccountList().set(changed_index, acc);
		//System.out.println(acc.getBalance());
		//System.out.println(accountList.getAccountList());
		client_map.replace(unique_id, accountList.getAccountList());
		//System.out.println(client_map.get(unique_id).get(0).getBalance());
	}

	private static void depositMoney(int unique_id) {
		// TODO Auto-generated method stub
		Account accc = null;
		int changed_index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter type of account you want to deposit \n 1.Savings \n 2.Current");
		String account_type = sc.next();
		System.out.println("Enter amount you want to deposit");
		double amount = sc.nextDouble();
		for (int i = 0; i < accountList.getAccountList().size(); i++) {
			if (account_type.equalsIgnoreCase(savings_account)) {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(savings_account)) {
					//System.out.println(accountList.getAccountList().get(i));
					accc = accountList.getAccountList().get(i);
					changed_index = i;
				}
			} else {
				if (accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(current_account)) {
					accc = accountList.getAccountList().get(i);
					changed_index = i;
				}
			}
		}
		Account acc = accc;
		acc.deposit(amount);
		accountList.getAccountList().set(changed_index, acc);
		//System.out.println(acc.getBalance());
		//System.out.println(accountList.getAccountList());
		client_map.replace(unique_id, accountList.getAccountList());
		//System.out.println(client_map.get(unique_id).get(0).getBalance());
	}

	private static void displayBalance(int unique_id) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter type of account which you want to display");
		String account = sc.next();
		for(int i =0;i<accountList.getAccountList().size();i++) {
			if(accountList.getAccountList().get(i).getAccount_type().equalsIgnoreCase(account)) {
				System.out.println("The balance for this account is" + accountList.getAccountList().get(i).getBalance() );
			}
		}
	}

}
