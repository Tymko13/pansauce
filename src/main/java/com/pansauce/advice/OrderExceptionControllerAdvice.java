package com.pansauce.advice;

import com.pansauce.exception.order.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionControllerAdvice {

    @ExceptionHandler({
            NonExistingOrderException.class,
            InvalidOrderException.class,
            NoOrdersFoundException.class
    })
    public ResponseEntity<String> nonExistingIngredientHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }

}
