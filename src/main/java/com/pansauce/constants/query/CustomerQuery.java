package com.pansauce.constants.query;

public class CustomerQuery {
    public static final String GET_ALL_CUSTOMERS = "SELECT * FROM customer";
    public static final String GET_CUSTOMER_BY_KEY = "SELECT * FROM customer WHERE customer_number = ?";
    public static final String ADD_CUSTOMER =  "INSERT INTO customer\n" +
            "(customer_number, customer_name, customer_surname, " +
            "customer_patronymic, customer_address)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_CUSTOMER_BY_KEY = "DELETE FROM customer WHERE customer_number = ?";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_PRICE =
            "SELECT * \n" +
            "FROM order\n" +
            "WHERE customer_number LIKE ?\n" +
            "ORDER BY total_order_cost;\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_REG_DATE =
            "SELECT * \n" +
            "FROM order\n" +
            "WHERE customer_number LIKE ?\n" +
            "ORDER BY registration_date;\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_EXP_DATE =
            "SELECT * \n" +
            "FROM order\n" +
            "WHERE customer_number LIKE ?\n" +
            "ORDER BY real_date;\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_PRICE =
            "SELECT *\n" +
            "FROM order AS o INNER JOIN contact_Number AS c\n" +
            "ON c.customer_number = o.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY o.total_order_cost;\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_REG_DATE =
            "SELECT *\n" +
            "FROM order AS o INNER JOIN contact_Number AS c\n" +
            "ON c.customer_number = o.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY o.registration_date;\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_EXP_DATE =
            "SELECT *\n" +
            "FROM order AS o INNER JOIN contact_Number AS c\n" +
            "ON c.customer_number = o.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY o.real_date;\n";

    public static final String GET_CUSTOMERS_BY_PIB =
            "SELECT *\n" +
            "FROM customer\n" +
            "WHERE customer_name LIKE ?\n" +
            "AND customer_surname LIKE ?\n" +
            "AND customer_patronymic LIKE ?;\n";

    public static final String GET_CUSTOMERS_WITH_THEIR_ORDERS =
            "SELECT *\n" +
            "FROM customer AS c INNER JOIN order AS o\n" +
            "ON c.customer_number = o.customer_number;\n";

    public static final String GET_CUSTOMERS_WHO_HAVE_ORDERS_BETWEEN_DATES =
            "SELECT\n" +
            "c.customer_number\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address\n" +
            "FROM customer AS c INNER JOIN order AS o\n" +
            "ON o.customer_number = c.customer_number\n" +
            "WHERE registration_date BETWEEN ?  AND ?\n" +
            "ORDER BY customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_TYPE_NUMBER =
            "SELECT c.customer_number\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address\n" +
            "FROM (((customer AS c INNER JOIN order AS o\n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b\n" +
            "ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number) INNER JOIN type AS t\n" +
            "ON t.Type_number = s.Type_number\n" +
            "WHERE type_number LIKE ?\n" +
            "order by c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_TYPE_NAME =
            "SELECT c.customer_number\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address\n" +
            "FROM (((customer AS c INNER JOIN order AS o\n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b\n" +
            "ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number) INNER JOIN type AS t\n" +
            "ON t.Type_number = s.Type_number\n" +
            "WHERE type_name LIKE ?\n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_SAUCE_NUMBER =
            "SELECT c.customer_number\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address\n" +
            "FROM ((customer AS c INNER JOIN order AS o \n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b \n" +
            "ON b.order_number=o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number\n" +
            "WHERE s.sauce_number LIKE ?\n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_SAUCE_NAME =
            "SELECT c.customer_number\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address\n" +
            "FROM ((customer AS c INNER JOIN order AS o \n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b \n" +
            "ON b.order_number=o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number\n" +
            "WHERE s.sauce_name LIKE ?\n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMER_WHO_ORDERED_BATCH_NUMBER =
            "SELECT c.customer_number\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address\n" +
            "FROM (customer AS c INNER JOIN order AS o \n" +
            "ON c.customer_number = o.customer_number) INNER JOIN batch AS b\n" +
            "ON b.order_number = o.order_number\n" +
            "WHERE batch_number LIKE ?\n";

}