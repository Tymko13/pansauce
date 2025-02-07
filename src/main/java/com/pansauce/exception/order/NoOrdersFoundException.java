package com.pansauce.exception.order;

import com.pansauce.constants.enums.OrderErrorMessage;

public class NoOrdersFoundException extends RuntimeException {

    public NoOrdersFoundException() {
        super(OrderErrorMessage.NO_ORDERS_FOUND.toString());
    }

}
