package com.pansauce.advice;

import com.pansauce.exception.phone.InvalidPhoneException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PhoneExceptionControllerAdvice {
    @ExceptionHandler({
            InvalidPhoneException.class
    })
    public ResponseEntity<String> nonExistingIngredientHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }
}