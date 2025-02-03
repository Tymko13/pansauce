package com.pansauce.constants.query;

public class OrderQuery {
    public static final String GET_ALL_ORDERS = "SELECT * FROM order ORDER BY registration_date ASC";
    public static final String GET_ORDER_BY_KEY = "SELECT * FROM order WHERE order_number = ?";
    public static final String ADD_ORDER = "INSERT INTO order " +
                                            "(order_number, registration_date, expected_date, " +
                                            "real_date, delivery_cost, total_order_cost) " +
                                            "VALUES(?, ?, ?, ?, ?, ?)";
    public static final String DELETE_ORDER_BY_KEY = "DELETE FROM order WHERE order_number = ?";
}
