package com.pansauce.constants.query;

public class OrderQuery {
    public static final String GET_ALL_ORDERS = "SELECT * FROM order ORDER BY registration_date ASC";
    public static final String GET_ORDER_BY_KEY = "SELECT * FROM order WHERE order_number = ?";
    public static final String ADD_ORDER = "INSERT INTO order " +
                                            "(order_number, registration_date, expected_date, " +
                                            "real_date, delivery_cost, total_order_cost) " +
                                            "VALUES(?, ?, ?, ?, ?, ?)";
    public static final String DELETE_ORDER_BY_KEY = "DELETE FROM order WHERE order_number = ?";

    public static final String GET_ALL_ORDERS_SORTED_BY_REG_DATE =
            "SELECT *\n" +
            "FROM order\n" +
            "ORDER BY registration_date\n";

    public static final String GET_ALL_ORDERS_SORTED_BY_PRICE =
            "SELECT *\n" +
            "FROM order\n" +
            "ORDER BY total_order_cost;\n";

    public static final String GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_PROD_DATE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "ORDER BY batch.production_date;\n";

    public static final String GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_PRICE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch.order_number LIKE ?\n" +
            "ORDER BY batch.batch_cost;\n";

    public static final String GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_NUMBER =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch.order_number LIKE ?\n" +
            "ORDER BY batch.batch_number;\n";

    public static final String GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_SAUCE_QUANTITY =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch.order_number LIKE ?\n" +
            "ORDER BY batch.sauce_quantity;\n";

    public static final String GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_STATUS =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch.order_number LIKE ?\n" +
            "ORDER BY batch.batch_status;\n";


}