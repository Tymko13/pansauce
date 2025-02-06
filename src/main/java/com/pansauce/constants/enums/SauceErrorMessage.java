package com.pansauce.constants.enums;

public enum SauceErrorMessage {

    NON_EXISTING_SAUCE("No sauce with such key exists");

    private final String message;

    SauceErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
