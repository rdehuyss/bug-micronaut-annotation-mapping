package com.example.demo.services;

import com.example.annotations.MyExternalAnnotation;
import com.example.annotations.MyInternalAnnotation;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);

    public MyService() {
        LOGGER.info("MyService constructor");
    }

    @MyExternalAnnotation(id = "some-id")
    public void myExternalAnnotatedMethodUsingMapper() {
        LOGGER.info("myExternalAnnotatedMethodUsingMapper");
        System.out.println("myExternalAnnotatedMethodUsingMapper");
    }

    @MyInternalAnnotation(id = "some-id")
    public void myInternalAnnotatedMethodWithoutMapper() {
        LOGGER.info("myInternalAnnotatedMethodWithoutMapper");
        System.out.println("myInternalAnnotatedMethodWithoutMapper");
    }

    public void myTestMethod() {
        LOGGER.info("myTestMethod");
        System.out.println("myTestMethod");
    }
}
