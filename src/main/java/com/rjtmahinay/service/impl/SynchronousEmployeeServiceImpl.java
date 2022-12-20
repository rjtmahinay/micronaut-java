package com.rjtmahinay.service.impl;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.repository.SynchronousEmployeeRepository;
import com.rjtmahinay.service.SynchronousEmployeeService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

/**
 * @author rjtmahinay
 * @project micronaut-java
 * @created 12/21/2022
 */
@Singleton
public class SynchronousEmployeeServiceImpl implements SynchronousEmployeeService {
    @Inject
    private SynchronousEmployeeRepository synchronousEmployeeRepository;

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return synchronousEmployeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return synchronousEmployeeRepository.findAllEmployee();
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return synchronousEmployeeRepository.update(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        synchronousEmployeeRepository.deleteById(id);
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return synchronousEmployeeRepository.save(employee);
    }
}
