package com.pansauce.advice;

import org.springframework.http.ResponseEntity;

public class ResponseEntityExceptionBuilder {

    private ResponseEntityExceptionBuilder() {}

    public static ResponseEntity<String> build(Exception ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

}