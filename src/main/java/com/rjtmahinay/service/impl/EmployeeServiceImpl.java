package com.rjtmahinay.service.impl;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.repository.EmployeeRepository;
import com.rjtmahinay.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * micronaut-java
 * @author rjtmahinay
 */
@Singleton
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployee();
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return employeeRepository.update(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return employeeRepository.save(employee);
    }
}
