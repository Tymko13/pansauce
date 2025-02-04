package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.ErrorMessage;

import java.util.List;

public class WeightAttributeValidator extends AttributeValidator {

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
        return ErrorMessage.NOT_SPECIFIED_WIGHT.toString();
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        double weight = Double.parseDouble(getAttribute());
        if (weight < minValue)
            errorMessages.add(ErrorMessage.WEIGHT_LESS_THAN_MIN.toString());
        else if (weight > maxValue)
            errorMessages.add(ErrorMessage.WEIGHT_MORE_THAN_MAX.toString());
    }

    public void setMin(double minValue) {
        this.minValue = minValue;
    }

    public void setMax(double maxValue) {
        this.maxValue = maxValue;
    }
}
