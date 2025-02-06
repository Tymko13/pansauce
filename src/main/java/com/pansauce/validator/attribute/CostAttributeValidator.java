package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

import java.math.BigDecimal;
import java.util.List;

public class CostAttributeValidator extends AttributeValidator{

    public CostAttributeValidator(String attribute, AttributeValidator nextValidator) {
        super(attribute, nextValidator);
    }

    public CostAttributeValidator(String attribute) {
        super(attribute);
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_COST.toString();
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        BigDecimal cost = new BigDecimal(getAttribute());
        if (cost.compareTo(BigDecimal.ZERO) < 0.0)
            errorMessages.add(AttributeErrorMessage.NON_POSITIVE_COST.toString());
    }
}