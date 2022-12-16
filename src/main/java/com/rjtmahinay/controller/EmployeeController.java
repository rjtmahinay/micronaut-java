package com.rjtmahinay.controller;

import com.rjtmahinay.json.EmployeeResponse;
import com.rjtmahinay.service.EmployeeService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/v1")
public class EmployeeController {
    @Inject
    private HttpClient httpClient;
    @Inject
    private EmployeeService employeeService;

    @Get(uri="/data", produces = MediaType.TEXT_JSON)
    public Mono<String> getData() {
        return Mono.just("Example Response");
    }

    @Get(uri="/datalist", produces = MediaType.TEXT_JSON)
    public Flux<String> getListOfData() {
        return Flux.just("Example 1", "Example 2");
    }

    @Get(uri="/retrieveData", produces = MediaType.TEXT_JSON)
    public Mono<String> retrieveSomeData() {
        return Mono.from(httpClient.retrieve("http://localhost:8080/v1/data"));
    }

    @Get(uri = "/display")
    public Mono<EmployeeResponse> display() {
        return Mono.fromSupplier(() -> employeeService.getEmployee());
    }

}