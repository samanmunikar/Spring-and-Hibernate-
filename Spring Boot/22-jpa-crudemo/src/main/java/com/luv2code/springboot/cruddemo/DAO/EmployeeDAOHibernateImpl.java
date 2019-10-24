package com.luv2code.springboot.cruddemo.DAO;

import com.luv2code.springboot.cruddemo.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    //define field for EntityManager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //create a query
        String queryString = "from employee";
        Query<Employee> theQuery =
                currentSession.createQuery(queryString, Employee.class);

        //execute query and get the result list
        List<Employee> employees = theQuery.getResultList();

        //return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //get the employee
        Employee theEmployee =
                currentSession.get(Employee.class, theId);

        //return the result
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //save or update the employee
        /* if id = 0  then save/insert else update */
        currentSession.saveOrUpdate(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
    //get the current hibernate session
    Session currentSession = entityManager.unwrap(Session.class);

    //delete the object with primary key
    Query theQuery =
                    currentSession.createQuery("delete from employee where id=:employeeId");
    theQuery.setParameter("employeeId", theId);

    theQuery.executeUpdate();
    }
}
