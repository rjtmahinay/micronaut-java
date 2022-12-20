/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.service.impl;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.repository.ReactiveEmployeeRepository;
import com.rjtmahinay.service.ReactiveEmployeeService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class ReactiveEmployeeServiceImpl implements ReactiveEmployeeService {
    @Inject
    private ReactiveEmployeeRepository reactiveEmployeeRepository;

    @Override
    public Mono<Employee> getEmployee(Long id) {
        return reactiveEmployeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> getAllEmployees() {
        return reactiveEmployeeRepository.findAllEmployee();
    }

    @Override
    public Mono<Employee> updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return reactiveEmployeeRepository.update(employee);
    }

    @Override
    public Mono<Long> deleteEmployee(Long id) {
        return reactiveEmployeeRepository.deleteById(id);
    }

    @Override
    public Mono<Employee> addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return reactiveEmployeeRepository.save(employee);
    }
}
