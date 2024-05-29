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

    // define custom sql query with index params
    @Query(value="select * from employees e where e.first_name = ?1 and e.last_name=?2",nativeQuery = true)
    Employee findByNativeSQL(String firstName,String lastName);

    // custom sql query with named params
    @Query(value="select * from employees e where e.first_name =:firstName and e.last_name=:lastName",nativeQuery = true)
    Employee findByNativeSQLWithNamedParams(String firstName,String lastName);

}
