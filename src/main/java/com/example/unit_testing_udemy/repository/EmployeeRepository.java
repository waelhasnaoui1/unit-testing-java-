package com.example.unit_testing_udemy.repository;

import com.example.unit_testing_udemy.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);


}
