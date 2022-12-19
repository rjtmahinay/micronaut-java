/**
 * micronaut-java
 *
 * @author rjtmahinay
 */
package com.rjtmahinay.controller;

import com.rjtmahinay.dto.EmployeeDto;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@Slf4j
public class EmployeeControllerTest {
    @Inject
    private EmployeeController employeeController;

    @Client("/v1/employee")
    @Inject
    private HttpClient httpClient;

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void returnAddedEmployee() throws IOException {

        EmployeeDto expectedEmployeeDto = mockEmployeeDto();
        String result = httpClient.toBlocking().retrieve(HttpRequest.POST("/add", expectedEmployeeDto));

        EmployeeDto actualEmployeeDto = objectMapper.readValue(result, EmployeeDto.class);

        assertEquals(expectedEmployeeDto, actualEmployeeDto);
    }

//    @Test
//    void returnEmployeeById() {
//        EmployeeDto expectedEmployeeDto = mockEmployeeDto();
//
//        String result = httpClient.toBlocking().retrieve(HttpRequest.);
//
//    }

//    private void createData() {
//        HttpRequest.POST("/add");
//        httpClient.toBlocking().exchange()
//    }
    private EmployeeDto mockEmployeeDto() {
        return new EmployeeDto("testName", "testAddress", "testPostion",
                BigDecimal.valueOf(1.0));
    }
}
