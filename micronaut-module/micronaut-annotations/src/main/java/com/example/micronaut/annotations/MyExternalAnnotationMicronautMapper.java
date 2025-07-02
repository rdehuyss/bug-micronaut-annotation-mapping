package com.example.micronaut.annotations;

import com.example.annotations.MyExternalAnnotation;
import io.micronaut.context.annotation.Executable;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.inject.annotation.TypedAnnotationMapper;
import io.micronaut.inject.visitor.VisitorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyExternalAnnotationMicronautMapper implements TypedAnnotationMapper<MyExternalAnnotation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExternalAnnotationMicronautMapper.class);

    @Override
    public Class<MyExternalAnnotation> annotationType() {
        return MyExternalAnnotation.class;
    }

    @Override
    public List<AnnotationValue<?>> map(AnnotationValue<MyExternalAnnotation> annotation, VisitorContext visitorContext) {
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
