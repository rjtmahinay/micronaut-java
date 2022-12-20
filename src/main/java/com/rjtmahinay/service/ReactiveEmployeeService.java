/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.service;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * micronaut-java
 * @author rjtmahinay
 */
public interface ReactiveEmployeeService {
    Mono<Employee> getEmployee(Long id);

    Mono<Employee> addEmployee(EmployeeDto employeeDto);

    Flux<Employee> getAllEmployees();

    Mono<Employee> updateEmployee(Long id, EmployeeDto employeeDto);

    Mono<Long> deleteEmployee(Long id);
}
