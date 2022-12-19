/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable public record EmployeeDto(@JsonProperty String name, @JsonProperty String address, @JsonProperty String position, @JsonProperty BigDecimal salary) {
}
