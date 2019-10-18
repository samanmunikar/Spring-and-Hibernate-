package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			//option 2: Hibernate query with HQL
			
			
			//get the instructor from db
			int theId = 7;

			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "JOIN FETCH i.courses "
							+ "where i.id = :theInstructorId", Instructor.class);
			
			//set the parameter on query(Pass :theInstructorId = theID)
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor(// Load the instructor and courses all at once)
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Luv2Code:  Instructor : " + tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();

			//close the session
			session.close();
			
			System.out.println("Luv2code: the session is now closed!\n");
			
			//should fail because session already closed before for lazy retrieval
			
			// **option 1: call getter method while session is open
			
			//retrieve the courses for instructor
			System.out.println("Luv2Code: Course : " + tempInstructor.getCourses());
			
			System.out.println("Done");
		}
		finally {
			//add clean up code
			session.close();
			factory.close();
		}

	}

}
