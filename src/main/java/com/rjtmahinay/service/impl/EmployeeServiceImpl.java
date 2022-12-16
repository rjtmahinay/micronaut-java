package com.rjtmahinay.service.impl;

import com.rjtmahinay.json.EmployeeResponse;
import com.rjtmahinay.service.EmployeeService;
import io.micronaut.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;

/**
 * micronaut-java
 * @author rjtmahinay
 */
@Bean
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeResponse getEmployee() {
        return new EmployeeResponse(1, "");
    }
}
