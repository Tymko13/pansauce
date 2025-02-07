package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

public class ProductNameAttributeValidator extends TextAttributeValidator {

    private static final String NAME_REGEX = "^[a-zA-Z а-щА-ЩЬьЮюЯяЇїІіЄєҐґ]*$";

    public ProductNameAttributeValidator(
            String productName, AttributeValidator nextValidator) {
        super(productName, nextValidator);
    }

    public ProductNameAttributeValidator(String productName) {
        super(productName);
    }

    @Override
    public String getInvalidErrorMessage() {
        return AttributeErrorMessage.INVALID_PRODUCT_NAME.toString();
    }

    @Override
    public String getRegex() {
        return NAME_REGEX;
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_PRODUCT_NAME.toString();
    }
}
