package com.pansauce.validator.attribute;

import com.pansauce.constants.enums.AttributeErrorMessage;

import java.util.List;

public class ShelfLifeAttributeValidator extends AttributeValidator {

    public ShelfLifeAttributeValidator(String shelfLife) {
        super(shelfLife);
    }

    public ShelfLifeAttributeValidator(String shelfLife, AttributeValidator nextValidator) {
        super(shelfLife, nextValidator);
    }

    @Override
    public String getRequiredErrorMessage() {
        return AttributeErrorMessage.NOT_SPECIFIED_SHELF_LIFE.toString();
    }

    @Override
    public void validateValue(List<String> errorMessages) {
        double shelfLife = Integer.parseInt(getAttribute());
        if (shelfLife < 0)
            errorMessages.add(AttributeErrorMessage.NEGATIVE_SHELF_LIFE.toString());
    }
}
