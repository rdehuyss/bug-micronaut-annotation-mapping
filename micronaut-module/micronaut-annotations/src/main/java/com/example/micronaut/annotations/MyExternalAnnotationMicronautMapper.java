package com.example.micronaut.annotations;

import com.example.annotations.MyExternalAnnotation;
import io.micronaut.annotation.processing.visitor.JavaVisitorContext;
import io.micronaut.context.annotation.Executable;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.inject.annotation.TypedAnnotationMapper;
import io.micronaut.inject.visitor.VisitorContext;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import java.util.List;

public class MyExternalAnnotationMicronautMapper implements TypedAnnotationMapper<MyExternalAnnotation> {

    static {
        System.err.println(">>> RecurringAnnotationMapper loaded");
    }

    public MyExternalAnnotationMicronautMapper() {
        System.err.println(">>> RecurringAnnotationMapper constructor called");
    }

    @Override
    public Class<MyExternalAnnotation> annotationType() {
        return MyExternalAnnotation.class;
    }

    @Override
    public List<AnnotationValue<?>> map(AnnotationValue<MyExternalAnnotation> annotation, VisitorContext visitorContext) {
        if (visitorContext instanceof JavaVisitorContext javaCtx) {
            ProcessingEnvironment pe = javaCtx.getProcessingEnv();
            Messager m = pe.getMessager();
            m.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "Hello from the JDK Messager");
        }
        visitorContext.warn("mapping annotation: " + annotation, null);
//        return List.of(AnnotationValue.builder(annotation)
//                .stereotype(AnnotationValue.builder(Executable.class)
//                        .member("processOnStartup", true)
//                        .build())
//                .stereotype(AnnotationValue.builder(ReflectiveAccess.class).build())
//                .build());
        AnnotationValue<Executable> executable = AnnotationValue.builder(Executable.class)
                .member("processOnStartup", true)
                .build();
        return List.of(AnnotationValue.builder(annotation)
                .stereotype(executable)
                .build()
        );
    }
}
