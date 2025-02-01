package com.pansauce.constants.query;

public class SauceQuery {

    public static final String GET_ALL_SAUCE = "SELECT * FROM sauce ORDER BY sauce_name ASC";
    public static final String GET_SAUCE_BY_KEY = "SELECT * FROM sauce WHERE sauce_number = ?";
    public static final String ADD_SAUCE = "INSERT INTO sauce" + '\n' +
                                           "(sauce_number, sauce_name, sauce_type," + '\n' +
                                           " shelf_life, sauce_weight, sauce_cost)" + '\n' +
                                           " VALUES (?, ?, ?, ?, ?, ?)";
    public static final String DELETE_SAUCE_BY_KEY = "DELETE FROM sauce WHERE sauce_number = ?";
}
