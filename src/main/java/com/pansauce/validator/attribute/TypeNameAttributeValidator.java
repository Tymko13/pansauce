package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

public class TypeNameAttributeValidator extends TextAttributeValidator{

    private static final String NAME_REGEX = "^[a-zA-Z а-щА-ЩЬьЮюЯяЇїІіЄєҐґ]*$";

    public TypeNameAttributeValidator(String typeName, AttributeValidator nextValidator) {
        super(typeName, nextValidator);
    }

    public TypeNameAttributeValidator(String typeName) {
        super(typeName);
    }

    @Override
    public String getInvalidErrorMessage() {
        return AttributeErrorMessage.INVALID_TYPE_NAME.toString();
    }

    @Override
    public String getRegex() {
        return NAME_REGEX;
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_TYPE_NAME.toString();
    }
}
