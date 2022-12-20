/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.repository;

import com.rjtmahinay.entity.Employee;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveEmployeeRepository extends ReactorCrudRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e")
    Flux<Employee> findAllEmployee();
}