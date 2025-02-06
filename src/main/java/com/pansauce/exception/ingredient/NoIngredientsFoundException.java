package com.pansauce.exception.ingredient;

import com.pansauce.constants.enums.IngredientErrorMessage;

public class NoIngredientsFoundException extends RuntimeException{

    public NoIngredientsFoundException() {
        super(IngredientErrorMessage.NO_INGREDIENTS_FOUND.toString());
    }
}
