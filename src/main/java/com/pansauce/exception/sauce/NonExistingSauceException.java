package com.pansauce.exception.sauce;

import com.pansauce.constants.enums.SauceErrorMessage;

public class NonExistingSauceException extends RuntimeException {
    public NonExistingSauceException() {
        super(SauceErrorMessage.NON_EXISTING_SAUCE.toString());
    }
}
