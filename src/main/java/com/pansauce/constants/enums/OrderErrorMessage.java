package com.pansauce.constants.enums;

public enum OrderErrorMessage {

    NON_EXISTING_ORDER("No order with such key exists"),
    NO_ORDERS_FOUND("No orders were found");

    private final String message;

    OrderErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
