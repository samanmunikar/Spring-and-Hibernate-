package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		//get the membership bean from spring container
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		//call the business method
		Account myAccount = new Account();
		myAccount.setName("Saman");
		myAccount.setLevel("Platinum");
		accountDAO.addAccount(myAccount, true);
		accountDAO.doWork();
		
		//call the accountdao getter/setter methods
		accountDAO.getName();
		accountDAO.setServiceCode("silver");
		
		//call membership business method
		membershipDAO.addAccount();
		
		//close the context
		context.close();
	}

}
