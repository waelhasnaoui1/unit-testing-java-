package com.example.unit_testing_udemy.service;

import com.example.unit_testing_udemy.model.Employee;
import com.example.unit_testing_udemy.repository.EmployeeRepository;
import com.example.unit_testing_udemy.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTests {

    /// using @Mock to to mock the needed object
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();
        MockitoAnnotations.openMocks(this);

        /// mocking using Mockito.mock
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    @DisplayName("testing were are saving an employee")
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployee(){
        // given
        /// we add this because mployeeService.saveEmployee call the method employeeRepository.save() so we need to mock it 
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        // when
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println("the saved employee is"+savedEmployee);
        // then

//        Assertions.assertThat(savedEmployee).isNull();
        ///// herer were verifying that the method employeeRepository.save() is called once
        verify(employeeRepository, times(1)).save(employee);

        /// here we want to verify that employeeService.saveEmployee() will not return an empty object

        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
