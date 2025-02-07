package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

import java.util.List;

public class WeightAttributeValidator extends AttributeValidator {

    private boolean hasLimits;
    private double minValue;
    private double maxValue;

    public WeightAttributeValidator(String weight, AttributeValidator nextValidator) {
        super(weight, nextValidator);
    }

    public WeightAttributeValidator(String weight) {
        super(weight, null);
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_WIGHT.toString();
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        double weight = Double.parseDouble(getAttribute());
        if (!hasLimits) return;
        if (weight < minValue)
            errorMessages.add(AttributeErrorMessage.WEIGHT_LESS_THAN_MIN.toString());
        else if (weight > maxValue)
            errorMessages.add(AttributeErrorMessage.WEIGHT_MORE_THAN_MAX.toString());
    }

    public void setMin(double minValue) {
        this.minValue = minValue;
        hasLimits = true;
    }

    public void setMax(double maxValue) {
        this.maxValue = maxValue;
        hasLimits = true;
    }
}
