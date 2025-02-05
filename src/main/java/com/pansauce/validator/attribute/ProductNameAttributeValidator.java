package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.ErrorMessage;

public class ProductNameAttributeValidator extends TextAttributeValidator {

    private static final String NAME_REGEX = "^[a-zA-Z ]*$";

    public ProductNameAttributeValidator(
            String productName, AttributeValidator nextValidator) {
        super(productName, nextValidator);
    }

    public ProductNameAttributeValidator(String productName) {
        super(productName);
    }

    @Override
    public String getInvalidErrorMessage() {
        return ErrorMessage.INVALID_PRODUCT_NAME.toString();
    }

    @Override
    public String getRegex() {
        return NAME_REGEX;
    }

    @Override
    public String getRequiredErrorMessage() {
        return ErrorMessage.NOT_SPECIFIED_PRODUCT_NAME.toString();
    }
}
