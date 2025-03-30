package com.pansauce.validator.model;

import com.pansauce.model.Phone;
import com.pansauce.validator.attribute.NumberAttributeValidator;

import java.util.List;

public class PhoneValidator implements ModelValidator<Phone> {
    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

    @Override
    public List<String> validate(Phone phone) {
        buildChain(phone);
        return validatorHandler.validate();
    }

    private void buildChain(Phone phone) {
        chainDeliveryCostValidator(phone);
    }

    private void chainDeliveryCostValidator(Phone phone) {
        String phoneNumber = phone.getPhoneNumber();
        NumberAttributeValidator phoneValidator
                = new NumberAttributeValidator(phoneNumber);
        validatorHandler.chain(phoneValidator);
    }

}