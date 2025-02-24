package com.pansauce.exception.type;

import java.util.List;

public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException(List<String> errorMessages) {
        super(String.join("\n", errorMessages));
    }
}
