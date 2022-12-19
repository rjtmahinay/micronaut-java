/**
 * micronaut-java
 * @author rjtmahinay
 */
package com.rjtmahinay.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(beans = {EmployeeException.class, ExceptionHandler.class})
public class EmployeeExceptionHandler implements ExceptionHandler<EmployeeException, HttpResponse<?>> {

    @Inject
    private ErrorResponseProcessor<?> errorResponseProcessor;

    @Override
    public HttpResponse<?> handle(HttpRequest request, EmployeeException exception) {
        return errorResponseProcessor.processResponse(
                ErrorContext.builder(request)
                        .cause(exception)
                        .errorMessage(exception.getMessage()).build(), HttpResponse.badRequest());
    }
}
