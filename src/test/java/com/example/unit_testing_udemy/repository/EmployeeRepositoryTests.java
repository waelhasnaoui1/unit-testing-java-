package com.example.unit_testing_udemy.repository;

import com.example.unit_testing_udemy.model.Employee;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
    @DisplayName("test update employee")
    public void givenEmployee_whenUpdating_thenGetNewEmployeeWithUpdatedValue(){
        // given
        Employee employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();
        // when
        Long id = employeeRepository.save(employee).getId();

        Employee foundEmployee = employeeRepository.findById(id).get();

        foundEmployee.setEmail("wael2@gmail.com");
        foundEmployee.setFirstName("wael2");
        foundEmployee.setLastName("has2");

        Employee updatedEmployee = employeeRepository.save(foundEmployee);


        // then
        Assertions.assertThat(updatedEmployee).isNotNull();
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("wael2@gmail.com");
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("wael2");
        Assertions.assertThat(updatedEmployee.getLastName()).isEqualTo("has2");
    }

    @Test
    @DisplayName("test delete employee")
    public void givenEmployee_whenDelete_thenThereIsNoEmployee(){
        // given
        Employee employee = Employee.builder().firstName("wael").lastName("has").email("wael@gmail.com").build();
        Long id = employeeRepository.save(employee).getId();

        // when
        employeeRepository.delete(employee);

        Optional<Employee> findEmployee = employeeRepository.findById(id);

        // then

        Assertions.assertThat(findEmployee).isEqualTo(Optional.empty());
        ///OR
        Assertions.assertThat(findEmployee).isEmpty();
    }

    ///Junit test for custom query using JPQl with index 1?
    @Test
    @DisplayName("test Find employee by JPQL")
    public void givenFirstAndLastName_whenFindByIdJPQL_thenReturnEmployeeObject(){
        // given
        String firstName = "wael";
        String lastName = "has";

        Employee employee = Employee.builder().firstName(firstName).lastName(lastName).email("wael@gmail.com").build();
        employeeRepository.save(employee);

        // when

        Employee foundedEmployee = employeeRepository.findByJPQL(firstName,lastName);

        // then

        Assertions.assertThat(foundedEmployee).isNotNull();
    }


    ///Junit test for custom query using JPQl with named parameter

    @Test
    public void given_when_then(){
        // given


        // when

        // then
    }


}
