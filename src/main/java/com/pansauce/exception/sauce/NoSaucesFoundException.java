package com.pansauce.exception.sauce;

import com.pansauce.constants.enums.SauceErrorMessage;

public class NoSaucesFoundException extends RuntimeException {
    public NoSaucesFoundException() {
        super(SauceErrorMessage.NO_SAUCES_FOUND.toString());
    }
}
