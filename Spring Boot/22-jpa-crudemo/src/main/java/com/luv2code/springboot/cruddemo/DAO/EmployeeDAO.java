package com.luv2code.springboot.cruddemo.DAO;

import com.luv2code.springboot.cruddemo.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();
    public Employee findById(int theId);
    public void save (Employee theEmployee);
    public void deleteById (int theId);
}
