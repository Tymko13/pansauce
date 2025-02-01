package com.pansauce.constants.query;

public class IngredientQuery {
    public static final String GET_ALL_INGREDIENT = "SELECT * FROM ingredient";
    public static final String ADD_INGREDIENT = "INSERT INTO ingredient" + '\n' +
                                                "(gti_number, ingredient_name)" + '\n' +
                                                " VALUES (?, ?)";
}
