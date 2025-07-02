package com.example.factory;

import com.example.services.MyAnnotationService;
import com.example.services.MyOtherService;
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
