/**
 * MIT License
 * Copyright (c) 2022 Tristan Mahinay
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * micronaut-java
 * @author rjtmahinay
 * 2022
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

/**
 * Implementation of {@link ReactiveEmployeeService}.
 */
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
        var employee = new Employee();
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
        var employee = new Employee();
        employee.setName(employeeDto.name());
        employee.setAddress(employeeDto.address());
        employee.setPosition(employeeDto.position());
        employee.setSalary(employeeDto.salary());

        return reactiveEmployeeRepository.save(employee);
    }
}
