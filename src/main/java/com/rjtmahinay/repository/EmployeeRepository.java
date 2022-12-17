package com.rjtmahinay.repository;

import com.rjtmahinay.entity.Employee;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author rjtmahinay
 * @project micronaut-java
 * @created 12/16/2022
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllEmployee();
}