package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

import java.util.List;

public class EmailAttributeValidator extends AttributeValidator{
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9-_+]+(\\.[A-Za-z0-9-_+]+)*@[^-][A-Za-z0-9-+]+(\\.[A-Za-z0-9-+]+)*(\\.[A-Za-z]{2,})$";

    public EmailAttributeValidator(String email, AttributeValidator nextValidator) {
        super(email, nextValidator);
    }

    public EmailAttributeValidator(String email) {
        super(email);
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        String attribute = getAttribute();
        if (attribute.isEmpty() || !attribute.matches(EMAIL_REGEX))
            errorMessages.add(AttributeErrorMessage.INVALID_EMAIL.toString());
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_NUMBER.toString();
    }
}
