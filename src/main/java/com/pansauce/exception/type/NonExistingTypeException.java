package com.pansauce.exception.type;

import com.pansauce.constants.enums.TypeErrorMessage;

public class NonExistingTypeException extends RuntimeException {
    public NonExistingTypeException() {
        super(TypeErrorMessage.NON_EXISTING_TYPE.toString());
    }
}
