package com.pansauce.exception.customer;

import com.pansauce.constants.enums.CustomerErrorMessage;

public class NonExistingCustomerException extends RuntimeException {
    public NonExistingCustomerException() {
        super(CustomerErrorMessage.NON_EXISTING_CUSTOMER.toString());
    }
}
