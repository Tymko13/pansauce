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
            "SELECT *\n" +
            "FROM batch\n" +
            "WHERE batch.order_number LIKE ?\n" +
            "ORDER BY batch.production_date;\n";

    public static final String GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_PRICE =
            "SELECT *\n" +
            "FROM batch\n" +
            "WHERE batch.order_number LIKE ?\n" +
            "ORDER BY batch.batch_cost;\n";

}