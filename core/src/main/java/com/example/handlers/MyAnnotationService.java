package com.example.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAnnotationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAnnotationService.class);

    public MyAnnotationService() {
        LOGGER.info("MyAnnotationService constructor");
    }

    public void handleInternalAnnotationProcessing(String annotationId) {
        LOGGER.info("Annotation Processing using internal annotation with Executable annotation inherited is working: " + annotationId);
    }

    public void handleExternalAnnotationProcessing(String annotationId) {
        LOGGER.info("Annotation Processing using external annotation with Executable annotation added by mapper is working: " + annotationId);
        LOGGER.info("Woohoo!! Great success!");
    }
}
