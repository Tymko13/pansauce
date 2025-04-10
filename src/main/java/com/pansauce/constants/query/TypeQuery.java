package com.pansauce.constants.query;

public class TypeQuery {
    public static final String GET_ALL_TYPES = "SELECT * FROM type ORDER BY type_name ASC";
    public static final String GET_TYPE_BY_KEY = "SELECT * FROM type\n" +
                                                    "WHERE type_number = ?";
    public static final String ADD_TYPE = "INSERT INTO type" + '\n' +
            "(type_number, type_name)" + '\n' +
            " VALUES (?, ?)";
    public static final String DELETE_TYPE_BY_KEY = "DELETE FROM type WHERE type_number = ?";

    public static final String GET_SAUCES_OF_TYPE_BY_NUMBER_SORTED_BY_SAUCE_NAME =
            "SELECT *\n" +
            "FROM Sauce AS s INNER JOIN type AS t ON t.type_number = s.type_number\n" +
            "WHERE t.type_number LIKE ?\n" +
            "ORDER BY s.sauce_name;\n";

    public static final String GET_SAUCES_OF_TYPE_BY_NUMBER_SORTED_BY_SAUCE_PRICE =
            "SELECT *\n" +
            "FROM Sauce AS s INNER JOIN type AS t ON t.type_number = s.type_number\n" +
            "WHERE t.type_number LIKE ?\n" +
            "ORDER BY s.sauce_cost;\n";

    public static final String GET_SAUCES_OF_TYPE_BY_NAME_SORTED_BY_SAUCE_NAME =
            "SELECT *\n" +
            "FROM Sauce AS s INNER JOIN type AS t ON t.type_number = s.type_number\n" +
            "WHERE t.type_name LIKE ?\n" +
            "ORDER BY s.sauce_name;\n";

    public static final String GET_SAUCES_OF_TYPE_BY_NAME_SORTED_BY_SAUCE_PRICE =
            "SELECT *\n" +
            "FROM Sauce AS s INNER JOIN type AS t ON t.type_number = s.type_number\n" +
            "WHERE t.type_name LIKE ?\n" +
            "ORDER BY s.sauce_cost;\n";

    public static final String UPDATE_TYPE =
            "UPDATE type\n" +
            "SET \n" +
            "type_name = ?\n" +
            "WHERE type_number = ?\n";


}