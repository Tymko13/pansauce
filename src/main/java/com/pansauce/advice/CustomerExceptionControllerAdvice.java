package com.pansauce.advice;

import com.pansauce.exception.customer.InvalidCustomerException;
import com.pansauce.exception.customer.NoCustomersFoundException;
import com.pansauce.exception.customer.NonExistingCustomerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionControllerAdvice {

    @ExceptionHandler({
            InvalidCustomerException.class,
            NonExistingCustomerException.class,
            NoCustomersFoundException.class
    })
    public ResponseEntity<String> invalidSauceHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }

}