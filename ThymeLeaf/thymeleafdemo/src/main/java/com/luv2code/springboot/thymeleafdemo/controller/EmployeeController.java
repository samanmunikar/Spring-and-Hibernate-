package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    //load employee data
    private List<Employee> employees;

    @PostConstruct
    private void loadData() {

        //create employees
        Employee emp1 = new Employee(1, "Saman", "Munikar", "samanmunikar@gmail.com");
        Employee emp2 = new Employee(1, "Mark", "Gottr", "markgottr@gmail.com");
        Employee emp3 = new Employee(1, "John", "Doe", "johndoe@gmail.com");

        //create the list
        employees = new ArrayList<>();

        //add to the list
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
    }

    //add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        //add to the spring model
        theModel.addAttribute("employees", employees);

        return "list-employees";
    }
}
