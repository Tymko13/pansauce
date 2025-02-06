package com.pansauce.constants.enums;

public enum IngredientErrorMessage {

    NON_EXISTING_INGREDIENT("No ingredient with such key exists"),
    NO_INGREDIENTS_FOUND("No ingredients were found");

    private final String message;

    IngredientErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
