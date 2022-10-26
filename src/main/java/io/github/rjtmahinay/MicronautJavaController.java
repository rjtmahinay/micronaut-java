package io.github.rjtmahinay;

import io.micronaut.http.annotation.*;

@Controller("/micronautJava")
public class MicronautJavaController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}