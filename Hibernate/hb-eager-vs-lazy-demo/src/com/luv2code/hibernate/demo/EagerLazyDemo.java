package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId = 7;
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Luv2Code:  Instructor : " + theInstructor);
			
			System.out.println("Luv2Code: Course : " + theInstructor.getCourses());
			
			//commit transaction
			session.getTransaction().commit();

			//close the session
			session.close();
			
			System.out.println("Luv2code: the session is now closed!\n");
			
			//should fail because session already closed before for lazy retrieval
			
			// **option 1: call getter method while session is open
			
			//retrieve the courses for instructor
			System.out.println("Luv2Code: Course : " + theInstructor.getCourses());
			
			System.out.println("Done");
		}
		finally {
			//add clean up code
			session.close();
			factory.close();
		}

	}

}
