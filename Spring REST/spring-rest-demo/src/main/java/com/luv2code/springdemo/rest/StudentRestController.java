package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;

	// define @PostConstruct to load the student data .. only once

	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();

		theStudents.add(new Student("Saman", "Munikar"));
		theStudents.add(new Student("Susan", "Shakya"));
		theStudents.add(new Student("Poornima", "Patel"));
	}

	// define endpoint for /students
	@GetMapping("/students")
	public List<Student> getStudents() {
		return theStudents;
	}
	
	// define endpoint for /students/{studentId} - Return student at index
		@GetMapping("/students/{studentId}")
		public Student getStudent(@PathVariable int studentId) {
			//check the studentId against list size
			if ((studentId >= theStudents.size()) || (studentId < 0) ) {
				throw new StudentNotFoundException("Student id not found - " + studentId);
			}

			return theStudents.get(studentId);
		}

}
