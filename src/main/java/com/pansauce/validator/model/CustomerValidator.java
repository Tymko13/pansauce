package com.pansauce.validator.model;

import com.pansauce.model.customer.Customer;

import java.util.List;

public class CustomerValidator implements ModelValidator<Customer> {

    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

    @Override
    public List<String> validate(Customer customer) {
        //todo add name/surname/patronym/address validation
        return validatorHandler.validate();
    }

}