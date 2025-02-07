package com.pansauce.advice;

import com.pansauce.exception.ingredient.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IngredientExceptionControllerAdvice {

    @ExceptionHandler({
            NonExistingIngredientException.class,
            InvalidIngredientException.class,
            NoIngredientsFoundException.class
    })
    public ResponseEntity<String> nonExistingIngredientHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }

}
