package com.pansauce.exception.ingredient;

import java.util.List;

public class InvalidIngredientException extends RuntimeException{

    public InvalidIngredientException(List<String> errorMessages) {
        super(String.join("\n", errorMessages));
    }

}