package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	public String getName() {
		System.out.println("GetName()");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println("setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": addAccount()");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + "doWork()");
		return true;
	}
	
	//add a new method: findAccounts()
	public List<Account> findAccounts() {
		System.out.println("Account Found");
		List<Account> myAccounts = new ArrayList<>();
		//create some sample accounts
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Saman", "Platinum");
		
		//add them to the list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		
		return myAccounts;
	}
}
