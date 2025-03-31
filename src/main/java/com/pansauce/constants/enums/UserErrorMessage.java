package com.pansauce.constants.enums;

public enum UserErrorMessage {

    NON_EXISTING_USER("User not found");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}