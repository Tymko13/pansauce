package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.ErrorMessage;

import java.util.List;

public class NameAttributeValidator extends AttributeValidator {
    private static final String NAME_REGEX = "^[a-zA-Z ]*$";
    public NameAttributeValidator(
            String name, AttributeValidator nextValidator) {
        super(name, nextValidator);
    }

    public NameAttributeValidator(String attribute) {
        super(attribute);
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        String attribute = getAttribute();
        if (attribute.isEmpty() || !attribute.matches(NAME_REGEX))
            errorMessages.add(ErrorMessage.INVALID_NAME.toString());
    }

    @Override
    public String getRequiredErrorMessage() {
        return ErrorMessage.NOT_SPECIFIED_NAME.toString();
    }
}
