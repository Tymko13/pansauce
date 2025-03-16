package com.pansauce.exception.customer;


import com.pansauce.constants.enums.CustomerErrorMessage;

public class NoCustomersFoundException extends RuntimeException {
    public NoCustomersFoundException() {
      super(CustomerErrorMessage.NO_CUSTOMERS_FOUND.toString());
    }
}
