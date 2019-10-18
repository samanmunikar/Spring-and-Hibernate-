package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 5;
			
			//start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//save the student object
			System.out.println("Getting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//updating
			System.out.println("Deleting student : " + myStudent);
//			session.delete(myStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//bulk Delete
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Delete email for all students
			System.out.println("Deleting");
			session.createQuery("delete from Student where id = 4").executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}

	}

}
