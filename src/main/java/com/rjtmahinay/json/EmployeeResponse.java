package com.rjtmahinay.json;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author rjtmahinay
 * @project micronaut-java
 * @created 12/14/2022
 */
@JsonInclude
public record EmployeeResponse(int id, String name, String email) {
    public EmployeeResponse(int id, String email) {
        this(id, "Test Name", email);
    }
}
