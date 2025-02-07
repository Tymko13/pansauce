package com.pansauce.advice;

import com.pansauce.exception.sauce.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SauceExceptionControllerAdvice {

    @ExceptionHandler({
            InvalidSauceException.class,
            NonExistingSauceException.class,
            NoSaucesFoundException.class
            })
    public ResponseEntity<String> invalidSauceHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }

}
