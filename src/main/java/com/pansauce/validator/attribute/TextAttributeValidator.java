package com.pansauce.validator.attribute;

import java.util.List;

public abstract class TextAttributeValidator extends AttributeValidator {

    public TextAttributeValidator(
            String name, AttributeValidator nextValidator) {
        super(name, nextValidator);
    }

    public TextAttributeValidator(String attribute) {
        super(attribute);
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        String attribute = getAttribute();
        if (attribute.isEmpty() || !attribute.matches(getRegex()))
            errorMessages.add(getInvalidErrorMessage());
    }

    public abstract String getInvalidErrorMessage();
    public abstract String getRegex();

}
