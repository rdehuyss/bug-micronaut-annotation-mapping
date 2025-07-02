package com.example.demo.api;

import com.example.demo.services.MyService;
import com.example.handlers.MyOtherService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

@Controller("/api")
public class MyApiController {

    @Inject
    private MyService myService;

    @Inject
    private MyOtherService myOtherService;

    @Get
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return "Hello World from ApiController!<br />" +
                "You can:<br />" +
                "- <a href=\"/api/test/local-service\">A simple test using a local service</a><br />" +
                "- <a href=\"/api/test/module-service\">A simple test using a module service</a><br />"
                ;
    }

    @Get("/test/local-service")
    @Produces(MediaType.TEXT_HTML)
    public String testLocalService() {
        myService.myTestMethod();
        return "Ok!";
    }

    @Get("/test/module-service")
    @Produces(MediaType.TEXT_HTML)
    public String test() {
        myOtherService.handle("input");
        return "Ok!";
    }
}
