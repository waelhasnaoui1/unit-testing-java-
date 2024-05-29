package com.example.unit_testing_udemy.service;

import com.example.unit_testing_udemy.model.Employee;
import com.example.unit_testing_udemy.repository.EmployeeRepository;
import com.example.unit_testing_udemy.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class EmployeeServiceTests {


    private EmployeeRepository employeeRepository;


    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployee(){
        // given


        // when
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println("the saved employee is"+savedEmployee);
        // then

        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
