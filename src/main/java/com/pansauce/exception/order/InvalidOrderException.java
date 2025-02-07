package com.pansauce.exception.order;

import java.util.List;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(List<String> errorMessages) {
        super(String.join("\n", errorMessages));
    }

}
