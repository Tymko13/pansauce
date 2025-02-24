package com.pansauce.exception.type;

import com.pansauce.constants.enums.TypeErrorMessage;

public class NoTypesFoundException extends RuntimeException {
    public NoTypesFoundException() {
        super(TypeErrorMessage.NO_TYPES_FOUND.toString());
    }
}
