package com.pansauce.constants.query;

public class SauceQuery {

    public static final String GET_ALL_SAUCE = "SELECT * FROM Sauce";
    public static final String ADD_SAUCE = "INSERT INTO Sauce" + '\n' +
                                           "(sauce_number, sauce_name, sauce_type," + '\n' +
                                           " shelf_life, sauce_weight, sauce_cost)" + '\n' +
                                           " VALUES (?, ?, ?, ?, ?, ?)";

}
