package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.DAO.EmployeeDAO;
import com.luv2code.springboot.cruddemo.Entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    //expose "/employees" endpoint and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //expose "/employees/{employeeId}" endpoint and return employee by Id
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null ) {
            throw new RuntimeException("Employee id not found " + employeeId);
        }

        return employee;
    }

    //expose POST "/employees" endpoint and add new employee
    @PostMapping("/employees")
    public Employee addEmployee (@RequestBody Employee employee){
        //set id to 0 this will force a save of new item
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    //expose PUT "/employees/{employeeId}" and update an employee
    @PutMapping("employees")
    public Employee updateEmployee (@RequestBody Employee employee){
        employeeService.save(employee);
        return employee;
    }

    //expose DELETE "/employees/{employeeId}" and delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee (@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        //throw exception if null
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted employee with id " + employeeId;
    }
}
