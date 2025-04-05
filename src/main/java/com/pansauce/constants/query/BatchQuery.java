package com.pansauce.constants.query;

public class BatchQuery {

    public static final String GET_ALL_BATCH =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number \n" +
            "ORDER BY production_date\n";

    public static final String GET_BATCH_BY_KEY =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch_number = ?\n";

    public static final String ADD_BATCH =  "INSERT INTO batch\n" +
                                            "(batch_number, sauce_quantity, production_date, " +
                                            "expiration_date, sauce_cost_at_that_time, batch_cost, " +
                                            "batch_status, sauce_number, order_number)\n" +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String DELETE_BATCH_BY_KEY = "DELETE FROM batch WHERE batch_number = ?";

}