package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the objects
			Instructor instructor = 
					new Instructor("John", "Darby", "johndarby@gmail.com");
			
			InstructorDetail instructorDetail = 
					new InstructorDetail("https://www.youtube.com/johndarby", "Guitar");
			
			//associate the objects
			instructor.setInstructorDetail(instructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
			//
			//Note: this will ALSO save the instructordetail object
			//because of CascadeType.ALL
			//
			System.out.println("Saving Instructor " + instructor);
			session.save(instructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally {
			factory.close();
		}

	}

}
