package com.rjtmahinay.service;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author rjtmahinay
 * @project micronaut-java
 * @created 12/21/2022
 */
public interface SynchronousEmployeeService {
    Optional<Employee> getEmployee(Long id);

    Employee addEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
