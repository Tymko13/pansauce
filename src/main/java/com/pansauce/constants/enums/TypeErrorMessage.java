package com.pansauce.constants.enums;

public enum TypeErrorMessage {

    NON_EXISTING_TYPE("No type with such key exists"),
    NO_TYPES_FOUND("No types were found");

    private final String message;

    TypeErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}