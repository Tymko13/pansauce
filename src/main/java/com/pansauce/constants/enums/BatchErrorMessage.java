package com.pansauce.constants.enums;

public enum BatchErrorMessage {

    NON_EXISTING_BATCH("No batch with such key exists"),
    NO_BATCHES_FOUND("No batches were found");

    private final String message;

    BatchErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
