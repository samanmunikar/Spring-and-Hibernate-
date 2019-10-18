package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse = 
					new Course("Pacman - How to Score One Million Points");
			
			//save the course
			System.out.println("Saving the course " + tempCourse);
			session.save(tempCourse);
			System.out.println(" Saved Course");
			
			//create a student object
			Student saman = new Student("Saman", "Munikar", "samanmunikar@gmail.com");
			Student john = new Student("John", "Doe", "johndoe@gmail.com");
			
			//add students to the course
			tempCourse.addStudent(saman);
			tempCourse.addStudent(john);
			
			//save the students
			session.save(saman);
			session.save(john);
			
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
