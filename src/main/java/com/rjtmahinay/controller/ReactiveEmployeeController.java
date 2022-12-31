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
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.exception.EmployeeException;
import com.rjtmahinay.service.ReactiveEmployeeService;
import io.micronaut.cache.annotation.CacheInvalidate;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

import javax.validation.Valid;

/**
 * Consist of reactive APIs for demonstration.
 */
@ExecuteOn(TaskExecutors.IO)
@Controller("/v1/reactive/employee")
public class ReactiveEmployeeController {
    @Inject
    private ReactiveEmployeeService employeeService;

    @Operation(summary = "Gets a specific employee", description = "Returns an employee with a given id",
            responses = {
                    @ApiResponse(
                            description = "Returns the employee successfully",
                            responseCode = "200"
                    )
            }
    )
    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Cacheable("reactive-employee")
    @SingleResult
    public Publisher<Employee> getEmployee(Long id) {
        return employeeService.getEmployee(id);
    }

    @Operation(summary = "Get all employee", description = "Return all the employees",
            responses = {
                    @ApiResponse(
                            description = "Return the employees successfully",
                            responseCode = "200"
                    )
            }
    )
    @Get(produces = MediaType.APPLICATION_JSON)
    @CachePut("reactive-employee")
    public Publisher<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Add an employee",
            description = "Invoke creation of employee data. Return the added employee.",
            responses = {
                    @ApiResponse(
                            description = "Return the new employee successfully",
                            responseCode = "200"
                    )
            }
    )

    @Post(uri = "/add", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @CacheInvalidate("reactive-employee")
    @SingleResult
    public Publisher<Employee> createEmployee(@Body @Valid EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @Operation(summary = "Update an employee",
            description = "Invoke update to a specific employee",
            responses = {
                    @ApiResponse(
                            description = "Return the updated employee successfully",
                            responseCode = "200"
                    )
            }
    )
    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @CacheInvalidate("reactive-employee")
    @SingleResult
    public Publisher<Employee> updateEmployee(@PathVariable Long id, @Body @Valid EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

    @Operation(summary = "Delete an employee",
            description = "Invoke deletion to a specific employee",
            responses = {
                    @ApiResponse(
                            description = "Return a successful string",
                            responseCode = "200"
                    )
            }
    )
    @Delete(uri = "/delete/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @CacheInvalidate("reactive-employee")
    @SingleResult
    public Publisher<Long> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @Operation(summary = "Throws a sample exception",
            description = "Throws a sample exception for demonstration",
            responses = {
                    @ApiResponse(
                            description = "Return an employee exception",
                            responseCode = "500"
                    )
            }
    )
    @Get(uri = "/exception", produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Publisher<EmployeeException> exception() {
        throw new EmployeeException("Employee exception");
    }

    @Error
    public HttpResponse<JsonError> error(HttpRequest<?> request, Throwable e) {
        JsonError error = new JsonError("Exception in Reactive Controller: " + e.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>serverError()
                .body(error);
    }
}