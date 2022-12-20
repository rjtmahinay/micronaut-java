package com.rjtmahinay.repository;

import com.rjtmahinay.entity.Employee;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author rjtmahinay
 * @project micronaut-java
 * @created 12/21/2022
 */
public interface SynchronousEmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e")
    List<Employee> findAllEmployee();
}
