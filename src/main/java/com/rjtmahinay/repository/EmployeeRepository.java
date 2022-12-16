package com.rjtmahinay.repository;

import com.rjtmahinay.entity.Employee;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

/**
 * @author rjtmahinay
 * @project micronaut-java
 * @created 12/16/2022
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
