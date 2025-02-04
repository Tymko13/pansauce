package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.ErrorMessage;

import java.util.List;

public abstract class AttributeValidator {

    private AttributeValidator nextValidator;
    private final String attribute;
    private boolean isRequired;

    public AttributeValidator(String attribute, AttributeValidator nextValidator){
        this.nextValidator = nextValidator;
        this.attribute = attribute;
    }

    public void validate(List<String> errorMessages) {
        if (!validateRequired(errorMessages)) return;
        validateValue(errorMessages);
    }
    public boolean validateRequired(List<String> errorMessages) {
        if (getAttribute() == null && isRequired()) {
            errorMessages.add(getRequiredErrorMessage());
            return false;
        }
        return true;
    }
    public abstract String getRequiredErrorMessage();
    public abstract void validateValue(List<String> errorMessages);

    public boolean isRequired() {
        return isRequired;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setNextValidator(AttributeValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

}
