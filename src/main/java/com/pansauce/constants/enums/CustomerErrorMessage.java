package com.pansauce.constants.enums;

public enum CustomerErrorMessage {

    NON_EXISTING_CUSTOMER("No customer with such key exists"),
    NO_CUSTOMERS_FOUND("No customers were found");

    private final String message;

    CustomerErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}