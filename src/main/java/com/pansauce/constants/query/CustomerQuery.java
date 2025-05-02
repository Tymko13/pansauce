package com.pansauce.constants.query;

public class CustomerQuery {

    public static final String GET_ALL_CUSTOMERS =
            "SELECT *\n" +
            "FROM customer LEFT JOIN contact_number\n" +
            "ON customer.customer_number = contact_number.customer_number\n" +
            "ORDER BY customer.customer_surname;";

    public static final String GET_CUSTOMER_BY_KEY =
            "SELECT *\n" +
            "FROM customer LEFT JOIN contact_number\n" +
            "ON customer.customer_number = contact_number.customer_number\n" +
            "WHERE customer.customer_number = ?;\n";

    public static final String ADD_CUSTOMER =
            "INSERT INTO customer\n" +
            "(customer_number, customer_name, customer_surname, " +
            "customer_patronymic, customer_address)\n" +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String DELETE_CUSTOMER_BY_KEY =
            "DELETE FROM customer WHERE customer_number = ?";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_PRICE =
            "SELECT \n" +
            "o.order_number, o.registration_date, o.expected_date,\n" +
            "o.real_date, o.delivery_cost, o.total_order_cost,\n" +
            "c.customer_number, c.customer_name, c.customer_surname,\n" +
            "c.customer_patronymic, cn.contact_number\n" +
            "FROM (order AS o INNER JOIN customer AS c\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY o.total_order_cost\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_REG_DATE =
            "SELECT \n" +
            "o.order_number, o.registration_date, o.expected_date,\n" +
            "o.real_date, o.delivery_cost, o.total_order_cost,\n" +
            "c.customer_number, c.customer_name, c.customer_surname,\n" +
            "c.customer_patronymic, cn.contact_number\n" +
            "FROM (order AS o INNER JOIN customer AS c\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY o.registration_date\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_EXP_DATE =
            "SELECT \n" +
            "o.order_number, o.registration_date, o.expected_date,\n" +
            "o.real_date, o.delivery_cost, o.total_order_cost,\n" +
            "c.customer_number, c.customer_name, c.customer_surname,\n" +
            " c.customer_patronymic,  cn.contact_number\n" +
            "FROM (order AS o INNER JOIN customer AS c\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY o.real_date\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_PRICE =
            "SELECT \n" +
            "o.order_number, o.registration_date, o.expected_date,\n" +
            "o.real_date, o.delivery_cost, o.total_order_cost,\n" +
            "c.customer_number, c.customer_name, c.customer_surname,\n" +
            "c.customer_patronymic, cn.contact_number\n" +
            "FROM (order AS o INNER JOIN customer AS c\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE cn.contact_number LIKE ?\n" +
            "ORDER BY o.total_order_cost\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_REG_DATE =
            "SELECT \n" +
            "o.order_number, o.registration_date, o.expected_date,\n" +
            "o.real_date, o.delivery_cost, o.total_order_cost,\n" +
            "c.customer_number, c.customer_name, c.customer_surname,\n" +
            "c.customer_patronymic, cn.contact_number\n" +
            "FROM (order AS o INNER JOIN customer AS c\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE cn.contact_number LIKE ?\n" +
            "ORDER BY o.registration_date\n";

    public static final String GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_EXP_DATE =
            "SELECT \n" +
            "o.order_number, o.registration_date, o.expected_date,\n" +
            "o.real_date, o.delivery_cost, o.total_order_cost,\n" +
            "c.customer_number, c.customer_name, c.customer_surname,\n" +
            "c.customer_patronymic, cn.contact_number\n" +
            "FROM (order AS o INNER JOIN customer AS c\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE cn.contact_number LIKE ?\n" +
            "ORDER BY o.real_date\n";

    public static final String GET_CUSTOMERS_BY_PIB =
            "SELECT *\n" +
            "FROM customer LEFT JOIN contact_number\n" +
            "ON customer.customer_number = contact_number.customer_number \n" +
            "WHERE customer.customer_name LIKE ?\n" +
            "AND customer.customer_surname LIKE ?\n" +
            "AND customer.customer_patronymic LIKE ?;\n";

    public static final String GET_CUSTOMERS_WITH_NUMBER_STARTING_WITH_SORTED_BY_SURNAME =
            "SELECT *\n" +
            "FROM customer AS c INNER JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE c.customer_number LIKE ?\n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WITH_PHONE_NUMBER_STARTING_WITH_SORTED_BY_SURNAME =
            "SELECT *\n" +
            "FROM customer INNER JOIN contact_number\n" +
            "ON customer.customer_number = contact_number.customer_number\n" +
            "WHERE customer.customer_number IN (\n" +
            "SELECT c.customer_number\n" +
            "FROM customer AS c INNER JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE cn.contact_number LIKE ?\n" +
            "ORDER BY c.customer_surname\n" +
            ");\n";

    public static final String GET_CUSTOMERS_WITH_THEIR_ORDERS =
            "SELECT *\n" +
            "FROM (customer AS c INNER JOIN order AS o\n" +
            "ON c.customer_number = o.customer_number) INNER JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number;\n";

    public static final String GET_CUSTOMERS_WHO_HAVE_ORDERS_BETWEEN_DATES =
            "SELECT\n" +
            "c.customer_number,\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address,\n" +
            "cn.contact_number\n" +
            "FROM (customer AS c INNER JOIN order AS o\n" +
            "ON o.customer_number = c.customer_number)\n" +
            "LEFT JOIN contact_number AS cn \n" +
            "ON c.customer_number = cn.customer_number \n" +
            "WHERE o.registration_date BETWEEN ? AND ? \n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_TYPE_NUMBER =
            "SELECT c.customer_number,\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address,\n" +
            "cn.contact_number\n" +
            "FROM ((((customer AS c INNER JOIN order AS o \n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b \n" +
            "ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number) INNER JOIN type AS t \n" +
            "ON t.Type_number = s.Type_number) LEFT JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number \n" +
            "WHERE type_number LIKE ? \n" +
            "order by c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_TYPE_NAME =
            "SELECT c.customer_number,\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address,\n" +
            "cn.contact_number\n" +
            "FROM ((((customer AS c INNER JOIN order AS o \n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b \n" +
            "ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number) INNER JOIN type AS t \n" +
            "ON t.Type_number = s.Type_number) LEFT JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number \n" +
            "WHERE type_name LIKE ? \n" +
            "order by c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_SAUCE_NUMBER =
            "SELECT c.customer_number,\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address,\n" +
            "cn.contact_number\n" +
            "FROM (((customer AS c INNER JOIN order AS o \n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b \n" +
            "ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number) LEFT JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE s.sauce_number LIKE ? \n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_SAUCE_NAME =
            "SELECT c.customer_number,\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address,\n" +
            "cn.contact_number\n" +
            "FROM (((customer AS c INNER JOIN order AS o \n" +
            "ON o.customer_number = c.customer_number) INNER JOIN batch AS b \n" +
            "ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "ON s.sauce_number = b.sauce_number) LEFT JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE s.sauce_name LIKE ? \n" +
            "ORDER BY c.customer_surname;\n";

    public static final String GET_CUSTOMER_WHO_ORDERED_BATCH_NUMBER =
            "SELECT c.customer_number,\n" +
            "c.customer_name,\n" +
            "c.customer_surname,\n" +
            "c.customer_patronymic,\n" +
            "c.customer_address,\n" +
            "cn.contact_number\n" +
            "FROM ((customer AS c INNER JOIN order AS o\n" +
            "ON c.customer_number = o.customer_number) INNER JOIN batch AS b\n" +
            "ON b.order_number = o.order_number) LEFT JOIN contact_number AS cn\n" +
            "ON c.customer_number = cn.customer_number\n" +
            "WHERE batch_number LIKE ?\n";

    public static final String GET_CUSTOMER_FAVOURITE_SAUCE_BY_CUSTOMER_NUMBER =
            "SELECT s.sauce_name, s.sauce_number, COUNT(*) as total_batches_sold\n" +
            "FROM (sauce AS s\n" +
            "INNER JOIN batch AS b\n" +
            "ON s.sauce_number = b.sauce_number)\n" +
            "INNER JOIN order AS o\n" +
            "ON o.order_number = b.order_number\n" +
            "WHERE o.customer_number = ?\n" +
            "GROUP BY s.sauce_number, s.sauce_name\n" +
            "ORDER BY COUNT(s.sauce_number) DESC\n" +
            "LIMIT 1;\n";

    public static final String GET_ORDERS_DATE_BEFORE_WITH_CUSTOMER_KEY =
              """
              SELECT\s
              o.order_number,\s
              o.registration_date,\s
              o.expected_date,
              o.real_date,\s
              o.delivery_cost,\s
              o.total_order_cost,
              c.customer_number,\s
              c.customer_name,\s
              c.customer_surname,
              c.customer_patronymic,\s
              cn.contact_number
            FROM\s
              (order AS o
              INNER JOIN customer AS c ON o.customer_number = c.customer_number)
              INNER JOIN contact_number AS cn ON c.customer_number = cn.customer_number
            WHERE NOT EXISTS (
                SELECT *
                FROM batch AS b
                WHERE b.order_number = o.order_number
                  AND NOT EXISTS (
                      SELECT *
                      FROM customer AS c2
                      WHERE c2.customer_number = o.customer_number
                        AND c2.customer_surname = ?
                        AND o.registration_date >= ?
                  )
            );
            """;

    public static final String GET_CUSTOMERS_WHO_ORDERED_ALL_TYPES_OF_SAUCE =
            "SELECT *\n" +
            "FROM customer AS c\n" +
            "INNER JOIN contact_number AS ct\n" +
            "ON c.customer_number = ct.customer_number\n" +
            "WHERE NOT EXISTS (\n" +
            "SELECT *\n" +
            "FROM type AS st\n" +
            "WHERE NOT EXISTS (\n" +
            "\tSELECT *\n" +
            "\tFROM (sauce AS s\n" +
            "INNER JOIN batch AS b\n" +
            "ON s.sauce_number = b.sauce_number)\n" +
            "INNER JOIN order AS o\n" +
            "ON o.order_number = b.order_number\n" +
            "WHERE o.customer_number = c.customer_number\n" +
            "AND s.type_number = st.type_number \n" +
            ")\n" +
            ");\n";

    public static final String GET_CUSTOMERS_WHO_ORDERED_ONLY_ONE_TYPE_OF_SAUCE =
            "SELECT c1.customer_number, c1.customer_name, " +
            "c1.customer_surname, c1.customer_patronymic, " +
            "c1.customer_address, cn1.contact_number\n" +
            "FROM customer AS c1\n" +
            "INNER JOIN contact_number AS cn1\n" +
            "ON c1.customer_number = cn1.customer_number\n" +
            "WHERE c1.customer_number IN (\n" +
            "    SELECT customer_number\n" +
            "    FROM (\n" +
            "        SELECT DISTINCT c.customer_number, s.type_number\n" +
            "        FROM ((customer AS c INNER JOIN [order] AS o\n" +
            "        ON o.customer_number = c.customer_number) INNER JOIN batch AS b\n" +
            "        ON b.order_number = o.order_number) INNER JOIN sauce AS s\n" +
            "        ON s.sauce_number = b.sauce_number)\n" +
            "    GROUP BY customer_number\n" +
            "    HAVING COUNT(*) = 1\n" +
            ");\n";

    public static final String GET_CUSTOMERS_ORDER_DATA =
            "SELECT c.customer_number, c.customer_surname," +
            "COUNT(o.order_number) AS total_orders_count," +
            "SUM(o.total_order_cost) AS total_orders_price\n" +
            "FROM (customer AS c\n" +
            "INNER JOIN order AS o ON c.customer_number = o.customer_number)\n" +
            "INNER JOIN batch AS b ON o.order_number = b.order_number\n" +
            "GROUP BY c.customer_number, c.customer_surname\n" +
            "ORDER BY total_orders_price DESC;\n";

    public static final String UPDATE_CUSTOMER =
            "UPDATE customer\n" +
            "SET \n" +
            "customer_name = ?,\n" +
            "customer_surname = ?,\n" +
            "customer_patronymic = ?,\n" +
            "customer_address = ?\n" +
            "WHERE customer_number = ?\n";

}