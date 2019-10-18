package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Get the student object
			System.out.println("Getting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//updating
			System.out.println("Updating student : " + myStudent);
			myStudent.setFirstName("Scooby");
			
			//commit transaction
			session.getTransaction().commit();
			
			//bulk update
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("Updating emails for all students: \n");
			session.createQuery("update Student set email='foo@gmail.com'")
							.executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}

	}

}
