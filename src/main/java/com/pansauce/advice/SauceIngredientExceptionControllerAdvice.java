package com.pansauce.advice;

import com.pansauce.exception.ingredient.InvalidIngredientException;
import com.pansauce.exception.ingredient.NoIngredientsFoundException;
import com.pansauce.exception.ingredient.NonExistingIngredientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SauceIngredientExceptionControllerAdvice {

    @ExceptionHandler({
            InvalidIngredientException.class,
            NoIngredientsFoundException.class,
            NonExistingIngredientException.class
    })
    public ResponseEntity<String> nonExistingIngredientHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }

}
