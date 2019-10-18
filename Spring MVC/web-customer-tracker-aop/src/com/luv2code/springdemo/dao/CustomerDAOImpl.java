package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create query
		Query theQuery = 
				session.createQuery("from Customer order by lastName", Customer.class);
		
		//execute the query and get the result list form the query
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//save the customer ...  finally to database
		session.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//retrieve from database using the primary key
		Customer theCustomer = session.get(Customer.class, theId);
				
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//delete object with the primary key
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
        
		Query theQuery = null;
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
		//Get the list of customers
		theQuery = session.createQuery("from Customer "
				+ "where lower(firstName) like :theName OR lower(lastName) like :theName", Customer.class);
		theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		System.out.println(theSearchName);
		System.out.println(theQuery.getParameterValue("theName"));
        }
        else {
            // theSearchName is empty ... so just get all customers
        	theQuery = session.createQuery("from Customer", Customer.class);            
        }
        //execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		System.out.println(customers);
		return customers;
	}

}
