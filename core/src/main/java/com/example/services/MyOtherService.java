package com.example.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyOtherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyOtherService.class);

    public MyOtherService() {
        LOGGER.info("MyOtherService constructor");
    }

    public void handle(String input) {
        LOGGER.info("Handling input: " + input);
    }
}
