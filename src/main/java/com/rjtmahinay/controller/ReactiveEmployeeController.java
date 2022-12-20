/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.controller;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.service.ReactiveEmployeeService;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Slf4j
@Controller("/v1/employee/reactive")
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
    @ExecuteOn(TaskExecutors.IO)
    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
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
    @ExecuteOn(TaskExecutors.IO)
    @Get(produces = MediaType.APPLICATION_JSON)
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
    @ExecuteOn(TaskExecutors.IO)
    @Post(uri = "/add", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Publisher<Employee> createEmployee(@Body EmployeeDto employeeDto) {
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
    @ExecuteOn(TaskExecutors.IO)
    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Publisher<Employee> updateEmployee(@PathVariable Long id, @Body EmployeeDto employeeDto) {
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
    @ExecuteOn(TaskExecutors.IO)
    @Delete(uri = "/delete/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Publisher<Long> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}