package com.example.unit_testing_udemy.repository;

import com.example.unit_testing_udemy.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);

    // define custom query using JPQL with index params
    @Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
    Employee findByJPQL(String firstName,String lastName);

    // define custom query using JPQL with named params

    @Query("select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
    Employee findByJPQLNamedParams(String firstName,String lastName);


}
