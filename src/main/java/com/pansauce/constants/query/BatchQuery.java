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

    public static final String GET_ALL_BATCH_SORTED_BY_NUMBER =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "ORDER BY batch_number;\n";

    public static final String GET_ALL_BATCH_SORTED_BY_SAUCE_QUANTITY =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "ORDER BY sauce_quantity;\n";

    public static final String GET_ALL_BATCH_SORTED_BY_PROD_DATE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "ORDER BY production_date;\n";

    public static final String GET_ALL_BATCH_SORTED_BY_STATUS =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "ORDER BY batch_status;\n";

    public static final String GET_ALL_BATCH_SORTED_BY_PRICE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "ORDER BY batch_cost;\n";

    public static final String GET_ALL_BATCH_WITH_STATUS_IN_STOCK_SORTED_BY_PROD_DATE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch_status = “IN STOCK”\n" +
            "ORDER BY production_date  DESC;\n";

    public static final String GET_ALL_BATCH_WITH_STATUS_SOLD_SORTED_BY_PROD_DATE =
            "SELECT batch_number, sauce_quantity, production_date,\n" +
            "expiration_date, sauce_cost_at_that_time, batch_cost, batch_status,\n" +
            "order_number, sauce_number, sauce_name\n" +
            "FROM batch INNER JOIN sauce ON batch.sauce_number = sauce.sauce_number\n" +
            "WHERE batch_status = “SOLD”\n" +
            "ORDER BY production_date  DESC;\n";

    public static final String GET_TOTAL_INCOME_FROM_SOLD_BATCHES_BETWEEN_DATES =
            "SELECT SUM(batch_cost) AS total_income\n" +
            "FROM batch AS b INNER JOIN order AS  o on o.order_number = b.order_number\n" +
            "WHERE batch_status = 'SOLD'\n" +
            "AND real_date BETWEEN ? AND ?\n";

    public static final String GET_TOTAL_AMOUNT_OF_SOLD_BATCHES_BETWEEN_DATES =
            "SELECT COUNT(batch_number) AS total_amount\n" +
            "FROM batch AS b INNER JOIN order AS  o on o.order_number = b.order_number\n" +
            "WHERE batch_status = 'SOLD'\n" +
            "AND real_date BETWEEN ? AND ?\n";

    public static final String GET_AMOUNT_OF_SOLD_BATCHES_BETWEEN_DATES_BY_SAUCE_KEY =
            "SELECT COUNT(batch_number) as total_amount\n" +
            "FROM (batch AS b INNER JOIN order AS o ON o.order_number = b.order_number) \n" +
            "INNER JOIN sauce AS s ON s.sauce_number = b.sauce_number\n" +
            "WHERE batch_status = “SOLD”\n" +
            "AND sauce_name = ? \n" +
            "AND real_date BETWEEN ? AND ?\n";

    public static final String GET_INCOME_FROM_SOLD_BATCHES_BETWEEN_DATES_BY_SAUCE_KEY =
            "SELECT SUM(batch_cost) AS total_income\n" +
            "FROM (batch AS b INNER JOIN order AS o ON o.order_number = b.order_number) \n" +
            "INNER JOIN sauce AS s ON s.sauce_number = b.sauce_number\n" +
            "WHERE batch_status = “SOLD”\n" +
            "AND sauce_name = ? \n" +
            "AND real_date BETWEEN ? AND ?\n";

    public static final String GET_AMOUNT_OF_SOLD_BATCHES_BETWEEN_DATES_BY_TYPE_KEY =
            "SELECT COUNT(batch_number) as total_amount\n" +
            "FROM ((batch AS b INNER JOIN order AS o ON o.order_number = b.order_number)\n" +
            "INNER JOIN sauce AS s ON s.sauce_number = b.sauce_number)\n" +
            "INNER JOIN type AS t ON t.type_number = s.type_number\n" +
            "WHERE batch_status = 'SOLD'\n" +
            "AND type_name = ?\n" +
            "AND real_date BETWEEN ? and ?\n";

    public static final String GET_INCOME_FROM_SOLD_BATCHES_BETWEEN_DATES_BY_TYPE_KEY =
            "SELECT SUM(batch_cost) AS total_income\n" +
            "FROM ((batch AS b INNER JOIN order AS o ON o.order_number = b.order_number)\n" +
            "INNER JOIN sauce AS s ON s.sauce_number = b.sauce_number)\n" +
            "INNER JOIN type AS t ON t.type_number = s.type_number\n" +
            "WHERE batch_status = 'SOLD'\n" +
            "AND type_name = ?\n" +
            "AND real_date BETWEEN ? and ?\n";

}