package com.pansauce.advice;

import com.pansauce.exception.type.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TypeExceptionControllerAdvice {
    @ExceptionHandler({
            NonExistingTypeException.class,
            InvalidTypeException.class,
            NoTypesFoundException.class
    })
    public ResponseEntity<String> nonExistingIngredientHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }
}