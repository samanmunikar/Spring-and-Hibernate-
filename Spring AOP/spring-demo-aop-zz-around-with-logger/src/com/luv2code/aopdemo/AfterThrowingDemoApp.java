package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		//call the business method
		List<Account> theAccounts = null;
		
		try{
		//add a boolean flag to simulate exceptions	
			boolean tripWire =  true;
			theAccounts = accountDAO.findAccounts(tripWire);
		}
		catch (Exception exc) {
			System.out.println("Main program ... caught exception: " + exc);
		}
		
		//display the accounts
		System.out.println("\n Main Program: AfterThrowingDemoApp");
		System.out.println("------");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		//close the context
		context.close();
	}

}
