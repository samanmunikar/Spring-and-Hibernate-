package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//display students with first name 'saman'
			theStudents = session.createQuery("from Student s where s.firstName = 'Saman'").getResultList();
			
			System.out.println("Student with first name saman: ");
			displayStudents(theStudents);
			
			//display student with first name saman and last name of munikar
			theStudents = session.createQuery("from Student s where"
						+ " s.lastName='Doe' OR s.firstName='Saman'").getResultList();
			
			displayStudents(theStudents);
			
			//query students where email like @gmail.com
			theStudents = session.createQuery("from Student s where"
								+ " s.email like '%gmail.com'").getResultList();
			System.out.println("Students with email gmail.com");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
