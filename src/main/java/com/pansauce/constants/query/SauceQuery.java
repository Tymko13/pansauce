package com.pansauce.constants.query;

public class SauceQuery {

    public static final String GET_ALL_SAUCE = "SELECT * FROM sauce ORDER BY sauce_name ASC";
    public static final String GET_SAUCE_BY_KEY = "SELECT * FROM sauce\n" +
                                                  "WHERE sauce_number = ?";
    public static final String ADD_SAUCE = "INSERT INTO sauce" + '\n' +
                                           "(sauce_number, sauce_name, sauce_type," + '\n' +
                                           " shelf_life, sauce_weight, sauce_cost)" + '\n' +
                                           " VALUES (?, ?, ?, ?, ?, ?)";
    public static final String DELETE_SAUCE_BY_KEY = "DELETE FROM sauce WHERE sauce_number = ?";
    public static final String GET_ALL_SAUCE_INGREDIENTS = "SELECT * FROM ingredient_sauce\n" +
                                                            "INNER JOIN ingredient\n" +
                                                            "   ON ingredient.gti_number = ingredient_sauce.gti_number\n" +
                                                            "WHERE sauce_number = ?";
    public static final String ADD_INGREDIENT_TO_SAUCE_BY_KEY = "INSERT INTO ingredient_sauce\n" +
                                                                "(sauce_number, gti_number, ing_weight)\n" +
                                                                "VALUES (?, ?, ?)";
}
