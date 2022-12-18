package com.rjtmahinay;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Main entry of a Micronaut Application.
 */
@OpenAPIDefinition(
    info = @Info(
            title = "micronaut-java",
            version = "v1",
            contact = @Contact (
                    name = "Tristan Mahinay",
                    email = "me@rjtmahinay.com",
                    url = "https://blog.rjtmahinay.com"
            )
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.build(args)
                .mainClass(Application.class)
                .start();
    }
}
