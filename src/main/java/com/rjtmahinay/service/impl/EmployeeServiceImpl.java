/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.service.impl;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.repository.EmployeeRepository;
import com.rjtmahinay.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployee();
    }

    @Override
    public Mono<Employee> updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return employeeRepository.update(employee);
    }

    @Override
    public Mono<Long> deleteEmployee(Long id) {
        return employeeRepository.deleteById(id);
    }

    @Override
    public Mono<Employee> addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return employeeRepository.save(employee);
    }
}
