package com.example.processors;

import com.example.annotations.MyInternalAnnotation;
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
public class MyInternalAnnotationMethodProcessor implements ExecutableMethodProcessor<MyInternalAnnotation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyInternalAnnotationMethodProcessor.class);

    private final MyAnnotationService myAnnotationService;

    public MyInternalAnnotationMethodProcessor(MyAnnotationService myAnnotationService) {
        this.myAnnotationService = myAnnotationService;
    }

    @Override
    public void process(BeanDefinition<?> beanDefinition, ExecutableMethod<?, ?> method) {
        LOGGER.info("mapping internal annotation: " + method);
        myAnnotationService.handleInternalAnnotationProcessing(getId(method));
    }

    private String getId(ExecutableMethod<?, ?> method) {
        return method.stringValue(MyInternalAnnotation.class, "id").orElse(null);
    }
}
