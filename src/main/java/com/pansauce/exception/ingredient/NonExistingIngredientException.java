package com.pansauce.exception.ingredient;

import com.pansauce.constants.enums.IngredientErrorMessage;

public class NonExistingIngredientException extends RuntimeException {

    public NonExistingIngredientException() {
        super(IngredientErrorMessage.NON_EXISTING_INGREDIENT.toString());
    }
}