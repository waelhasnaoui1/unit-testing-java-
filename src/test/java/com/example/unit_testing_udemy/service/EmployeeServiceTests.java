package com.example.unit_testing_udemy.service;

import com.example.unit_testing_udemy.exception.ResourceNotFoundException;
import com.example.unit_testing_udemy.model.Employee;
import com.example.unit_testing_udemy.repository.EmployeeRepository;
import com.example.unit_testing_udemy.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(null);

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


    @Test
    @DisplayName("testing were are saving an existing employee throw an exception")
    public void givenEmployeeObject_whenSavingExistEmployee_thenThrowAnException(){
        // given
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(employee);

        // when

        Assertions.assertThatThrownBy(()->employeeService.saveEmployee(employee)).isInstanceOf(ResourceNotFoundException.class);

        // then

        verify(employeeRepository,never()).save(employee);
    }

    @Test
    @DisplayName("testing getting all employees when calling getALL")
    public void givenListEmployee_whenCallingGetAllEmployee_thenReturnAListOfEmployees(){
        // given
        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(employee));

        // when
        List<Employee> employees = employeeService.getAllEmployee();


        // then
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees.size()).isEqualTo(1);
        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("testing getting all employees if we have an empty table calling getALL")
    public void givenEmptyEmployeeList_whenCallingGetAllEmployee_thenReturnAListOfListEmployees(){
        // given
        BDDMockito.given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<Employee> employees = employeeService.getAllEmployee();


        // then
        Assertions.assertThat(employees).isEmpty();
        Assertions.assertThat(employees.size()).isEqualTo(0);
        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Junit test for get employee by id")
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObj(){

        /// given
        BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));

        // when
        Optional<Employee> foundedEmployee = employeeService.getEmployeeById(employee.getId());

        // then
        Assertions.assertThat(foundedEmployee).isPresent();
        verify(employeeRepository,times(1)).findById(employee.getId());

    }

    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnTheUpdatedEmployee(){
        // given
        BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("wael2@gmail.com");
        employee.setFirstName("wael2");
        employee.setLastName("has2");
        // when
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then
        Assertions.assertThat(updatedEmployee).isNotNull();
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("wael2@gmail.com");
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("wael2");
        Assertions.assertThat(updatedEmployee.getLastName()).isEqualTo("has2");
    }

    @Test
    public void givenNotExistEmployee_whenUpdateEmployee_thenReturnTheUpdatedEmployee(){
        // given
        BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
//        employee.setEmail("wael2@gmail.com");
//        employee.setFirstName("wael2");
//        employee.setLastName("has2");
        // when
//        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then
        Assertions.assertThatThrownBy(()->employeeService.updateEmployee(employee)).isInstanceOf(ResourceNotFoundException.class);
//        Assertions.assertThat(updatedEmployee).isNotNull();
//        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("wael2@gmail.com");
//        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("wael2");
//        Assertions.assertThat(updatedEmployee.getLastName()).isEqualTo("has2");
    }


    @Test
    public void givenEmployee_whenDeleteEmployee_thenReturnVoid(){
        // given
        BDDMockito.given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        BDDMockito.willDoNothing().given(employeeRepository).delete(employee);


        // when
        employeeService.deleteEmployee(employee);
        // then
        verify(employeeRepository,times(1)).delete(employee);
    }

    @Test
    public void given_when_then(){
        // given



        // when

        // then
    }

}
