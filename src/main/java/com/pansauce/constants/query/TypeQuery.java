package com.pansauce.constants.query;

public class TypeQuery {
    public static final String GET_ALL_TYPES = "SELECT * FROM type ORDER BY type_name ASC";
    public static final String GET_TYPE_BY_KEY = "SELECT * FROM type\n" +
                                                    "WHERE type_number = ?";
    public static final String ADD_TYPE = "INSERT INTO type" + '\n' +
            "(type_number, type_name)" + '\n' +
            " VALUES (?, ?)";
    public static final String DELETE_TYPE_BY_KEY = "DELETE FROM type WHERE type_number = ?";
}