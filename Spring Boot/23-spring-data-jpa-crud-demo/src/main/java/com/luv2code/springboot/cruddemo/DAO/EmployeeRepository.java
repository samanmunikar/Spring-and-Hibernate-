package com.luv2code.springboot.cruddemo.DAO;

import com.luv2code.springboot.cruddemo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
