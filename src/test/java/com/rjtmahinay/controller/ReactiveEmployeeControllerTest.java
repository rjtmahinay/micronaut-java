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
package com.rjtmahinay.controller;

import com.rjtmahinay.dto.EmployeeDto;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class of {@link ReactiveEmployeeController}
 */
@MicronautTest
public class ReactiveEmployeeControllerTest {

    @Client("/v1/reactive/employee")
    @Inject
    private HttpClient httpClient;

    @Inject
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        httpClient.toBlocking().exchange(HttpRequest.POST("/add", mockEmployeeDto1()));
        httpClient.toBlocking().exchange(HttpRequest.POST("/add", mockEmployeeDto2()));
        httpClient.toBlocking().exchange(HttpRequest.POST("/add", mockEmployeeDto3()));
    }

    @Test
    void returnAddedEmployee() throws IOException {
        EmployeeDto expectedEmployeeDto = mockEmployeeDto1();
        String result = httpClient.toBlocking().retrieve(HttpRequest.POST("/add", expectedEmployeeDto));

        EmployeeDto actualEmployeeDto = objectMapper.readValue(result, EmployeeDto.class);

        assertEquals(expectedEmployeeDto, actualEmployeeDto);
    }

    @Test
    void returnEmployee() throws IOException {
        String result = httpClient.toBlocking().retrieve(HttpRequest.GET("/"));

        List<EmployeeDto> actualEmployeeDtos = objectMapper.readValue(result, Argument.listOf(EmployeeDto.class));

        assertEquals(mockEmployeeDto1(), actualEmployeeDtos.get(0));
    }

    @Test
    void returnEmployeeById() throws IOException {
        String result = httpClient.toBlocking().retrieve(HttpRequest.GET("/1"));

        EmployeeDto actualEmployeeDto = objectMapper.readValue(result, EmployeeDto.class);

        assertEquals(mockEmployeeDto1(), actualEmployeeDto);
    }

    @Test
    void returnUpdatedEmployee() throws IOException {
        EmployeeDto employeeDto = new EmployeeDto("testUpdatedName", "testAddress2",
                "testPosition2", BigDecimal.valueOf(2.0));
        String result = httpClient.toBlocking().retrieve(HttpRequest.PUT("/update/2", employeeDto));

        EmployeeDto actualEmployeeDto = objectMapper.readValue(result, EmployeeDto.class);

        assertEquals("testUpdatedName", actualEmployeeDto.name());
    }

    @Test
    void returnDeletedCount() {
        String result = httpClient.toBlocking().retrieve(HttpRequest.DELETE("/delete/3"));

        assertEquals(1, Long.valueOf(result));
    }

    @Test
    void returnException() {
        assertThrows(HttpClientResponseException.class, ()
                -> httpClient.toBlocking().retrieve(HttpRequest.GET("/exception")));
    }

    private EmployeeDto mockEmployeeDto1() {
        return new EmployeeDto("testName1", "testAddress1", "testPostion1",
                BigDecimal.valueOf(1.0));
    }
    private EmployeeDto mockEmployeeDto2() {
        return new EmployeeDto("testName2", "testAddress2", "testPostion2",
                BigDecimal.valueOf(2.0));
    }

    private EmployeeDto mockEmployeeDto3() {
        return new EmployeeDto("testName3", "testAddress3", "testPostion3",
                BigDecimal.valueOf(3.0));
    }
}
