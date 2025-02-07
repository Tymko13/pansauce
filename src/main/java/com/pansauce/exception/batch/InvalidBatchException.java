package com.pansauce.exception.batch;

import java.util.List;

public class InvalidBatchException extends RuntimeException {

    public InvalidBatchException(List<String> errorMessages) {
        super(String.join("\n", errorMessages));
    }

}
