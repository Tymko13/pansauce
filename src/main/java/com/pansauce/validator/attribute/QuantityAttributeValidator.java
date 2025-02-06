package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

import java.util.List;

public class QuantityAttributeValidator extends AttributeValidator {

    public QuantityAttributeValidator(String quantity, AttributeValidator nextValidator) {
        super(quantity, nextValidator);
    }

    public QuantityAttributeValidator(String quantity) {
        super(quantity);
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_QUANTITY.toString();
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        int quantity = Integer.parseInt(getAttribute());
        if (quantity <= 0)
            errorMessages.add(AttributeErrorMessage.NON_POSITIVE_COST.toString());
    }
}
