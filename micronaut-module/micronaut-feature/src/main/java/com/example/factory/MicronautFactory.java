package com.example.factory;

import com.example.handlers.MyAnnotationService;
import com.example.handlers.MyOtherService;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class MicronautFactory {

    @Singleton
    public MyAnnotationService myAnnotationService() {
        return new MyAnnotationService();
    }

    @Singleton
    public MyOtherService myOtherService() {
        return new MyOtherService();
    }
}
