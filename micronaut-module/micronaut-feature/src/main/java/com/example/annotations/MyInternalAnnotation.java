package com.example.annotations;

import io.micronaut.context.annotation.Executable;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Executable(processOnStartup = true)
@Documented
public @interface MyInternalAnnotation {

    /**
     * @return The id of this annotation
     */
    String id() default "";
}
