package com.rjtmahinay.controller;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;
import com.rjtmahinay.service.EmployeeService;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller("/v1/employee")
public class EmployeeController {
    @Inject
    private EmployeeService employeeService;

    @Operation(summary = "Gets a specific employee",
            description = "Returns an employee with a given id",
            responses = {
                @ApiResponse(content
                        = @Content(
                            mediaType = MediaType.APPLICATION_JSON
                        )
                )
            }

    )
    @ExecuteOn(TaskExecutors.IO)
    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Optional<Employee> getEmployee(Long id){
        return employeeService.getEmployee(id);
    }

    @ExecuteOn(TaskExecutors.IO)
    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @ExecuteOn(TaskExecutors.IO)
    @Post(uri = "/add", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Employee createEmployee(@Body EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @ExecuteOn(TaskExecutors.IO)
    @Put(uri = "/update/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @SingleResult
    public Employee updateEmployee(@PathVariable Long id, @Body EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id, employeeDto);
    }

    @ExecuteOn(TaskExecutors.IO)
    @Delete(uri = "/delete/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        log.info("Employee Deleted with ID: {}", id);
    }
}