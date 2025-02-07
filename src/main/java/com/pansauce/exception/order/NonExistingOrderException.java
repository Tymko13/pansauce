package com.pansauce.exception.order;

import com.pansauce.constants.enums.OrderErrorMessage;

public class NonExistingOrderException extends RuntimeException {

    public NonExistingOrderException() {
        super(OrderErrorMessage.NON_EXISTING_ORDER.toString());
    }

}