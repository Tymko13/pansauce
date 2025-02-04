package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.ErrorMessage;

import java.util.List;

public class NumberAttributeValidator extends AttributeValidator {
    private static final String NUMBER_REGEX = "^\\+[1-9][0-9]{11}$";
    public NumberAttributeValidator(
            String number, AttributeValidator nextValidator) {
        super(number, nextValidator);
    }

    public NumberAttributeValidator(String number) {
        super(number);
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        if (getAttribute().isEmpty() || !getAttribute().matches(NUMBER_REGEX))
            errorMessages.add(ErrorMessage.INVALID_NAME.toString());
    }

    @Override
    public String getRequiredErrorMessage() {
        return ErrorMessage.NOT_SPECIFIED_NUMBER.toString();
    }
}
