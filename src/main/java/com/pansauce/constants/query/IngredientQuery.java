package com.pansauce.constants.query;

public class IngredientQuery {
    public static final String GET_ALL_INGREDIENT = "SELECT * FROM ingredient ORDER BY ingredient_name ASC";
    public static final String GET_INGREDIENT_BY_KEY = "SELECT * FROM ingredient WHERE gti_number = ?";
    public static final String ADD_INGREDIENT = "INSERT INTO ingredient" + '\n' +
                                                "(gti_number, ingredient_name)" + '\n' +
                                                " VALUES (?, ?)";
    public static final String DELETE_INGREDIENT_BY_KEY = "DELETE FROM ingredient WHERE gti_number = ?";
}
