package com.pansauce.exception.phone;

import java.util.List;

public class InvalidPhoneException extends RuntimeException {

    public InvalidPhoneException(List<String> errorMessages) {
        super(String.join("\n", errorMessages));
    }

}
