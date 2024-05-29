package com.example.unit_testing_udemy.service.impl;

import com.example.unit_testing_udemy.exception.ResourceNotFoundException;
import com.example.unit_testing_udemy.model.Employee;
import com.example.unit_testing_udemy.repository.EmployeeRepository;
import com.example.unit_testing_udemy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee saveEmployee(Employee employee) {

        if (employeeRepository.findByEmail(employee.getEmail()) != null){
            throw new ResourceNotFoundException("Employee already exist with given email" + employee.getEmail());
        }

        return employeeRepository.save(employee);
    }
}
