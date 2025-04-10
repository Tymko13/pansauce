package com.pansauce.constants.query;

public class SauceQuery {

    public static final String GET_ALL_SAUCE = "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number";

    public static final String GET_SAUCE_BY_KEY = "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number\n" +
                                                  "WHERE sauce_number = ?";

    public static final String GET_SAUCE_BY_NAME_PREFIX =
            "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number\n" +
            "WHERE sauce_name LIKE ?";

    public static final String GET_SAUCE_BY_NUMBER_PREFIX =
            "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number\n" +
            "WHERE sauce_number LIKE ?";

    public static final String ADD_SAUCE = "INSERT INTO sauce" + '\n' +
                                           "(sauce_number, sauce_name, shelf_life," + '\n' +
                                           " sauce_weight, sauce_cost, type_number)" + '\n' +
                                           " VALUES (?, ?, ?, ?, ?, ?)";

    public static final String DELETE_SAUCE_BY_KEY = "DELETE FROM sauce WHERE sauce_number = ?";

    public static final String GET_ALL_SAUCE_INGREDIENTS = "SELECT * FROM ingredient_sauce\n" +
                                                            "INNER JOIN ingredient\n" +
                                                            "   ON ingredient.gti_number = ingredient_sauce.gti_number\n" +
                                                            "WHERE sauce_number = ?";

    public static final String ADD_INGREDIENT_TO_SAUCE_BY_KEY = "INSERT INTO ingredient_sauce\n" +
                                                                "(sauce_number, gti_number, ing_weight)\n" +
                                                                "VALUES (?, ?, ?)";

    public static final String GET_ALL_SAUCES_SORTED_BY_NAME = "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number ORDER BY sauce_name";
    public static final String GET_ALL_SAUCES_SORTED_BY_NUMBER = "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number ORDER BY sauce_number";
    public static final String GET_ALL_SAUCES_SORTED_BY_TYPE_NAME = "SELECT * FROM sauce INNER JOIN type ON sauce.type_number = type.type_number ORDER BY type_number";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_STATUS =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_number LIKE ? \n" +
            "ORDER BY batch_status\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_PRICE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_number LIKE ? \n" +
            "ORDER BY batch_cost\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_PROD_DATE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_number LIKE ? \n" +
            "ORDER BY production_date\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_SAUCE_QUANTITY =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_number LIKE ? \n" +
            "ORDER BY sauce_quantity\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_NUMBER =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_number LIKE ? \n" +
            "ORDER BY batch_number\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_STATUS =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_name LIKE ? \n" +
            "ORDER BY batch_status\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_PRICE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_name LIKE ? \n" +
            "ORDER BY batch_cost\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_PROD_DATE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_name LIKE ? \n" +
            "ORDER BY production_date\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_SAUCE_QUANTITY =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_name LIKE ? \n" +
            "ORDER BY sauce_quantity\n";

    public static final String GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_NUMBER =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name \n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "WHERE sauce_name LIKE ? \n" +
            "ORDER BY batch_number\n";

    public static final String GET_ALL_SAUCE_RECIPE_SORTED_BY_NAME =
            "SELECT * \n" +
            "FROM (ingredient_sauce AS is INNER JOIN ingredient AS i\n" +
            "ON ingredient.gti_number = ingredient_sauce.gti_number) INNER JOIN\n" +
            "sauce AS s ON is.sauce_number = sauce.sauce_number\n" +
            "WHERE s.sauce_number = ?\n" +
            "ORDER BY s.sauce_name\n";

    public static final String GET_ALL_SAUCE_RECIPE_SORTED_BY_WEIGHT =
            "SELECT * \n" +
            "FROM ((ingredient_sauce AS is INNER JOIN ingredient AS i\n" +
            "ON ingredient.gti_number = ingredient_sauce.gti_number) INNER JOIN\n" +
            "sauce AS s ON is.sauce_number = sauce.sauce_number) INNER JOIN\n" +
            "type ON type.type_number = s.type_number" +
            "WHERE s.sauce_number = ?\n" +
            "ORDER BY s.sauce_weight\n";

    public static final String GET_TOP_FIVE_SAUCE_BY_INCOME =
            "SELECT\n" +
            "s.sauce_name,\n" +
            "s.sauce_number,\n" +
            "SUM(b.batch_cost) AS sauce_income\n" +
            "FROM sauce AS s\n" +
            "INNER JOIN batch AS b on b.sauce_number = s.sauce_number\n" +
            "WHERE b.batch_status = 'sold'\n" +
            "GROUP BY s.sauce_name, s.sauce_number\n" +
            "ORDER BY sauce_income DESC\n" +
            "LIMIT 5\n";

    public static final String GET_LAST_FIVE_SAUCE_BY_INCOME =
            "SELECT\n" +
            "s.sauce_name, s.sauce_number,\n" +
            "SUM(b.batch_cost) AS sauce_income\n" +
            "FROM sauce AS s\n" +
            "INNER JOIN batch AS b on b.sauce_number = s.sauce_number\n" +
            "WHERE b.batch_status = 'sold'\n" +
            "GROUP BY s.sauce_name, s.sauce_number\n" +
            "ORDER BY sauce_income\n" +
            "LIMIT 5\n";

    public static final String GET_TOP_FIVE_SAUCE_BY_SALES_COUNT =
            "SELECT\n" +
            "s.sauce_name, s.sauce_number,\n" +
            "COUNT(b.batch_number) AS total_batches_sold\n" +
            "FROM sauce AS s\n" +
            "JOIN batch AS b ON b.sauce_number = s.sauce_number\n" +
            "WHERE b.batch_status = 'sold'\n" +
            "GROUP BY s.sauce_name, s.sauce_number\n" +
            "ORDER BY total_batches_sold DESC\n" +
            "LIMIT 5\n";

    public static final String GET_LAST_FIVE_SAUCE_BY_SALES_COUNT =
            "SELECT\n" +
            "s.sauce_name, s.sauce_number,\n" +
            "COUNT(b.batch_number) AS total_batches_sold\n" +
            "FROM sauce AS s\n" +
            "JOIN batch AS b ON b.sauce_number = s.sauce_number\n" +
            "WHERE b.batch_status = 'sold'\n" +
            "GROUP BY s.sauce_name, s.sauce_number\n" +
            "ORDER BY total_batches_sold\n" +
            "LIMIT 5\n";


    public static final String GET_TOP_FIVE_SAUCE_RECIPE_BY_INCOME =
            "SELECT *\n" +
            "FROM (ingredient AS i INNER JOIN ingredient_sauce AS is\n" +
            "ON i.gti_number = is.gti_number) INNER JOIN Sauce AS s\n" +
            "ON s.sauce_number = is.sauce_number\n" +
            "WHERE s.sauce_number IN (\n" +
            "\tSELECT s1.sauce_number\n" +
            "\tFROM Sauce AS s1 INNER JOIN batch AS b\n" +
            "\tON s1.sauce_number = b.sauce_number\n" +
            "\tGROUP BY s1.sauce_number\n" +
            "\tORDER BY SUM(b.batch_cost) DESC\n" +
             "\tLIMIT 5" +
            ")\n";

    public static final String GET_TOP_FIVE_SAUCE_RECIPE_BY_SALES_COUNT =
            "SELECT *\n" +
            "FROM (ingredient AS i INNER JOIN ingredient_sauce AS is\n" +
            "ON i.gti_number = is.gti_number) INNER JOIN Sauce AS s\n" +
            "ON s.sauce_number = is.sauce_number\n" +
            "WHERE s. sauce_number IN (\n" +
            "\tSELECT s1. sauce_number\n" +
            "\tFROM Sauce AS s1 INNER JOIN batch AS b\n" +
            "\tON s1.sauce_number = b.sauce_number\n" +
            "\tGROUP BY s1.sauce_number\n" +
            "\tORDER BY COUNT(b.batch_number) DESC\n" +
            "\tLIMIT 5\n" +
            ")\n";

    public static final String GET_LAST_FIVE_SAUCE_RECIPE_BY_INCOME =
            "SELECT *\n" +
            "FROM (ingredient AS i INNER JOIN ingredient_sauce AS is\n" +
            "ON i.gti_number = is.gti_number) INNER JOIN Sauce AS s\n" +
            "ON s.sauce_number = is.sauce_number\n" +
            "WHERE s.sauce_number IN (\n" +
            "\tSELECT s1.sauce_number\n" +
            "\tFROM Sauce AS s1 INNER JOIN batch AS b\n" +
            "\tON s1.sauce_number = b.sauce_number\n" +
            "\tGROUP BY s1.sauce_number\n" +
            "\tORDER BY SUM(b.batch_cost)\n" +
            "\tLIMIT 5\n" +
            ")\n";

    public static final String GET_LAST_FIVE_SAUCE_RECIPE_BY_SALES_COUNT =
            "SELECT *\n" +
            "FROM (ingredient AS i INNER JOIN ingredient_sauce AS is\n" +
            "ON i.gti_number = is.gti_number) INNER JOIN Sauce AS s\n" +
            "ON s.sauce_number = is.sauce_number\n" +
            "WHERE s. sauce_number IN (\n" +
            "\tSELECT s1. sauce_number\n" +
            "\tFROM Sauce AS s1 INNER JOIN batch AS b\n" +
            "\tON s1.sauce_number = b.sauce_number\n" +
            "\tGROUP BY s1.sauce_number\n" +
            "\tORDER BY COUNT(b.batch_number)\n" +
            "\tLIMIT 5\n" +
            ")\n";

    public static final String UPDATE_SAUCE =
            "UPDATE sauce\n" +
            "SET \n" +
            "sauce_name = ?,\n" +
            "shelf_life = ?,\n" +
            "sauce_weight = ?,\n" +
            "sauce_cost = ?,\n" +
            "typeNumber = ?\n" +
            "WHERE sauce_number = ?\n";

}