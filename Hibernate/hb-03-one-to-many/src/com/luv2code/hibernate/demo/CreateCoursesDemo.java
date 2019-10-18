package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			//create some courses
			Course java = new Course("Java");
			Course python = new Course("Python");
			
			//add courses to instructor
			theInstructor.add(java);
			theInstructor.add(python);
			
			//save the courses
			session.save(java);
			session.save(python);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			//add clean up code
			session.close();
			factory.close();
		}

	}

}
