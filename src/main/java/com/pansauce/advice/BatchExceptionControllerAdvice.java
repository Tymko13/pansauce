package com.pansauce.advice;

import com.pansauce.exception.batch.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BatchExceptionControllerAdvice {

    @ExceptionHandler({
            NonExistingBatchException.class,
            InvalidBatchException.class,
            NoBatchesFoundException.class
    })
    public ResponseEntity<String> nonExistingIngredientHandler(Exception ex) {
        return ResponseEntityExceptionBuilder.build(ex);
    }

}
