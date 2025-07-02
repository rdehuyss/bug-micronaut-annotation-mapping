package com.example.processors;

import com.example.annotations.MyExternalAnnotation;
import com.example.services.MyAnnotationService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.processor.ExecutableMethodProcessor;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.ExecutableMethod;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Requires(beans = {MyAnnotationService.class})
public class MyExternalAnnotationMethodProcessor implements ExecutableMethodProcessor<MyExternalAnnotation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExternalAnnotationMethodProcessor.class);

    private final MyAnnotationService myAnnotationService;

    public MyExternalAnnotationMethodProcessor(MyAnnotationService myAnnotationService) {
        this.myAnnotationService = myAnnotationService;
    }

    @Override
    public void process(BeanDefinition<?> beanDefinition, ExecutableMethod<?, ?> method) {
        LOGGER.info("mapping external annotation: " + method);
        myAnnotationService.handleExternalAnnotationProcessing(getId(method));
        LOGGER.info("Great success! Woohoo!!!");
    }

    private String getId(ExecutableMethod<?, ?> method) {
        return method.stringValue(MyExternalAnnotation.class, "id").orElse(null);
    }
}
