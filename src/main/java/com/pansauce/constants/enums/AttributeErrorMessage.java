package com.pansauce.constants.enums;

public enum AttributeErrorMessage {
    INVALID_PRODUCT_NAME("Invalid product name"),
    NOT_SPECIFIED_PRODUCT_NAME("Product name not specified"),
    INVALID_NUMBER("Invalid phone number"),
    NOT_SPECIFIED_NUMBER("Number not specified"),
    INVALID_EMAIL("Invalid email"),
    NOT_SPECIFIED_EMAIL("Email not specified"),
    NOT_SPECIFIED_WIGHT("Weight not specified"),
    WEIGHT_LESS_THAN_MIN("Weight less than min value"),
    WEIGHT_MORE_THAN_MAX("Weight more than max value"),
    INVALID_TYPE_NAME("Invalid type name"),
    NOT_SPECIFIED_TYPE_NAME("Type name not specified"),
    NEGATIVE_SHELF_LIFE("Negative shelf life"),
    NOT_SPECIFIED_SHELF_LIFE("Shelf life not specified"),
    NON_POSITIVE_COST("Non positive cost"),
    NOT_SPECIFIED_COST("Not specified cost"),
    NON_POSITIVE_QUANTITY("Non positive quantity"),
    NOT_SPECIFIED_QUANTITY("Quantity not specified");

    private final String message;

    AttributeErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
