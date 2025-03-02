package com.pansauce.constants.query;

public class UserQuery {

    public static final String GET_USER_BY_USERNAME = "SELECT * FROM user\n" +
                                                      "WHERE username = ?";

}