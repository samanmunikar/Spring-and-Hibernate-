package com.luv2code.springboot.cruddemo.DAO;

import com.luv2code.springboot.cruddemo.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    //constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //create a query
        Query theQuery =
                entityManager.createQuery("from Employee", Employee.class);

        //execute the query and get the employees
    List<Employee> employees= theQuery.getResultList();

        //return the employees
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //get employee
        Employee employee =
                entityManager.find(Employee.class, theId);

        //return the employee
        return employee;
    }

    @Override
    public void save(Employee theEmployee) {
    //save or update the employee
    Employee dbEmployee = entityManager.merge(theEmployee);

    //update with id from db ... so we can get generated id for save/insert
    theEmployee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int theId) {
    //delete object with primary key
    Query theQuery =
            entityManager.createQuery("delete from Employee where id=:employeeId");

    theQuery.setParameter("employeeId", theId);

    theQuery.executeUpdate();
    }
}
