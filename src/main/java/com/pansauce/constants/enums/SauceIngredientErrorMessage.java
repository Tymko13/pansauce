package com.pansauce.constants.enums;

public enum SauceIngredientErrorMessage {

    NON_EXISTING_INGREDIENT_OF_SAUCE("This sauce does not contains the given ingredient"),
    SAUCE_WITHOUT_RECIPE("This sauce does not have a recipe");

    private final String message;

    SauceIngredientErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
