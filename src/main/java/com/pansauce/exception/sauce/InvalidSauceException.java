package com.pansauce.exception.sauce;

import java.util.List;

public class InvalidSauceException extends RuntimeException {

    public InvalidSauceException(List<String> errorMessages) {
        super(String.join("\n", errorMessages));
    }

}