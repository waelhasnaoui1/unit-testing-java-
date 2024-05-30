package com.example.unit_testing_udemy.service;

import com.example.unit_testing_udemy.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
