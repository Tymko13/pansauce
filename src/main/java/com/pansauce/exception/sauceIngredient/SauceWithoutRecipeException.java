package com.pansauce.exception.sauceIngredient;

import com.pansauce.constants.enums.SauceIngredientErrorMessage;

public class SauceWithoutRecipeException extends RuntimeException {

    public SauceWithoutRecipeException() {
        super(SauceIngredientErrorMessage.SAUCE_WITHOUT_RECIPE.toString());
    }
}
