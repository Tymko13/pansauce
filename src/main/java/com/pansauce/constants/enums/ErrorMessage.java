package com.pansauce.constants.enums;

public enum ErrorMessage {
    INVALID_NAME("Invalid name"),
    NOT_SPECIFIED_NAME("Name not specified"),
    INVALID_NUMBER("Invalid phone number"),
    NOT_SPECIFIED_NUMBER("Number not specified"),
    INVALID_EMAIL("Invalid email"),
    NOT_SPECIFIED_EMAIL("Email not specified");
    private final String message;
    private ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
