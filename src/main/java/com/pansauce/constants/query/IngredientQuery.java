package com.pansauce.constants.query;

public class IngredientQuery {
    public static final String GET_ALL_INGREDIENT = "SELECT * FROM ingredient ORDER BY ingredient_name ASC";
    public static final String GET_INGREDIENT_BY_KEY = "SELECT * FROM ingredient WHERE gti_number = ?";
    public static final String ADD_INGREDIENT = "INSERT INTO ingredient" + '\n' +
                                                "(gti_number, ingredient_name)" + '\n' +
                                                " VALUES (?, ?)";
    public static final String DELETE_INGREDIENT_BY_KEY = "DELETE FROM ingredient WHERE gti_number = ?";

    public static final String GET_ALL_INGREDIENTS_SORTED_BY_NUMBER =
            "SELECT *\n" +
            "FROM ingredient\n" +
            "ORDER BY gti_number\n";

    public static final String GET_ALL_INGREDIENTS_SORTED_BY_NAME =
            "SELECT *\n" +
            "FROM ingredient\n" +
            "ORDER BY ingredient_name\n";

    public static final String GET_INGREDIENTS_WITH_NUMBER_STARTING_WITH_SORTED_BY_NUMBER =
            "SELECT *\n" +
            "FROM ingredient\n" +
            "WHERE gti_number LIKE ?\n" +
            "ORDER BY gti_number\n";

    public static final String GET_INGREDIENTS_WITH_NUMBER_STARTING_WITH_SORTED_BY_NAME =
            "SELECT *\n" +
            "FROM ingredient\n" +
            "WHERE gti_number LIKE ?\n" +
            "ORDER BY ingredient_name\n";

    public static final String GET_INGREDIENTS_WITH_NAME_STARTING_WITH_SORTED_BY_NUMBER =
            "SELECT *\n" +
            "FROM ingredient\n" +
            "WHERE ingredient_name LIKE ?\n" +
            "ORDER BY gti_number\n";

    public static final String GET_INGREDIENTS_WITH_NAME_STARTING_WITH_SORTED_BY_NAME =
            "SELECT *\n" +
            "FROM ingredient\n" +
            "WHERE ingredient_name LIKE ?\n" +
            "ORDER BY ingredient_name\n";

    public static final String UPDATE_INGREDIENT_WITH_NUMBER =
            "UPDATE ingredient\n" +
            "SET \n" +
            "ingredient_name = ?\n" +
            "WHERE gti_number = ?\n";

}