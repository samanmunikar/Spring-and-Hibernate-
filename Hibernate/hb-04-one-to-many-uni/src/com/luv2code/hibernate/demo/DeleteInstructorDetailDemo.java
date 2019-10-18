package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
			
			//start a transaction
			session.beginTransaction();
		
			//get the instructor detail object
			int instructorDetailId = 6;
			InstructorDetail instructorDetail = 
					session.get(InstructorDetail.class, instructorDetailId);
			
			//print instructor detail
			System.out.println("InstructorDetail : " + instructorDetail);
			
			//print the associated instructor
			System.out.println("Associated Instructor : " + instructorDetail.getInstructor());
			
			//delete the instructor delete
			System.out.println("Deleting the instructor Detail : " + instructorDetail);
			
			//remove the associated object reference
			//break bi-directional link
			
			instructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(instructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}

	}

}
