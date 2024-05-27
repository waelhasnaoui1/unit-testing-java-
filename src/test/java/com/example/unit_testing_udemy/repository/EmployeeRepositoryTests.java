package com.example.unit_testing_udemy.repository;

import com.example.unit_testing_udemy.model.Employee;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Junit test for save Employee")
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        // given

        Employee employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();

        // when

        Employee savedEmployee = employeeRepository.save(employee);

        // then

        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);


    }

    @Test
    @DisplayName("test getting all employee")
    public void givenEmployeeList_whenGetAll_thenReturnListOfEmployee(){
        // given
        Employee employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();
        Employee employee2 = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when

        List<Employee> employeeList = employeeRepository.findAll();

        // then
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("test get employee by id")
    public void givenEmployee_whenGetEmployeeById_thenReturnTheSameEmployee(){
        // given
        Employee employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();

        Long employeeId = employeeRepository.save(employee).getId();

        // when
        Employee foundEmployee = employeeRepository.findById(employeeId).get();
        // then
        Assertions.assertThat(foundEmployee).isNotNull();
    }

    @Test
    @DisplayName("test find employee by email")
    public void givenEmployee_whenFindByEmail_thenReturnEmployeeWithSameEmail(){
        // given
        Employee employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();

        employeeRepository.save(employee);

        // when
        Employee foundEmployee = employeeRepository.findByEmail(employee.getEmail());
        // then

        Assertions.assertThat(foundEmployee).isNotNull();


    }

    @Test
    public void given_when_then(){
        // given


        // when

        // then
    }


}
