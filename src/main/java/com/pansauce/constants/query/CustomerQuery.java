package com.pansauce.constants.query;

public class CustomerQuery {
    public static final String GET_ALL_CUSTOMERS = "SELECT * FROM customer ORDER BY name ASC";
    public static final String GET_CUSTOMER_BY_KEY = "SELECT * FROM customer WHERE customer_number = ?";
    public static final String ADD_CUSTOMER =  "INSERT INTO customer\n" +
            "(customer_number, customer_name, customer_surname, " +
            "customer_patronymic, customer_address)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_CUSTOMER_BY_KEY = "DELETE FROM customer WHERE customer_number = ?";
}
