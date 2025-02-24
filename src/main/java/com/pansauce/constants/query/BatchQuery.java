package com.pansauce.constants.query;

public class BatchQuery {
    public static final String GET_ALL_BATCH = "SELECT * FROM batch ORDER BY production_date ASC";
    public static final String GET_BATCH_BY_KEY = "SELECT * FROM batch WHERE batch_number = ?";
    public static final String ADD_BATCH =  "INSERT INTO batch\n" +
                                            "(batch_number, sauce_quantity, production_date, " +
                                            "expiration_date, sauce_cost_at_that_time, batch_cost, " +
                                            "batch_status, sauce_number, order_number)\n" +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_BATCH_BY_KEY = "DELETE FROM batch WHERE batch_number = ?";
}