package com.pansauce.repository;

import com.pansauce.dao.CustomerDao;
import com.pansauce.model.Customer;
import com.pansauce.model.CustomerWithOrders;
import com.pansauce.model.Order;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.CUSTOMER_KEY_LENGTH;
import static com.pansauce.constants.query.CustomerQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.*;

@Repository(value = "customerRepo")
public class CustomerRepository implements CustomerDao {

    private final JdbcTemplate jdbc;

    public CustomerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Customer customer, String customerKey) {
        jdbc.update(ADD_CUSTOMER,
                customerKey,
                customer.getName(),
                customer.getSurname(),
                customer.getPatronymic(),
                customer.getAddress());
    }

    @Override
    public void add(Customer customer) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(CUSTOMER_KEY_LENGTH);
        String customerKey = keyGenerator.nextString();
        insert(customer, customerKey);
    }

    @Override
    public List<Customer> findAll() {
        return jdbc.query(GET_ALL_CUSTOMERS, CUSTOMER_ROW_MAPPER);
    }

    @Override
    public Customer findByKey(String key) {
        List<Customer> result = jdbc.query(GET_CUSTOMER_BY_KEY, CUSTOMER_ROW_MAPPER, key);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void delete(String key) {
        jdbc.update(DELETE_CUSTOMER_BY_KEY, key);
    }

    @Override
    public boolean exists(String key) {
        return findByKey(key) != null;
    }

    @Override
    public List<Order> getCustomerOrdersByKeySortedByPrice(String customerKey) {
        return jdbc.query(GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_PRICE, ORDER_ROW_MAPPER, customerKey);
    }

    @Override
    public List<Order> getCustomerOrdersByKeySortedByRegDate(String customerKey) {
        return jdbc.query(GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_REG_DATE, ORDER_ROW_MAPPER, customerKey);
    }

    @Override
    public List<Order> getCustomerOrdersByKeySortedByExpDate(String customerKey) {
        return jdbc.query(GET_ORDERS_OF_CUSTOMER_BY_CUSTOMER_KEY_SORTED_BY_EXP_DATE, ORDER_ROW_MAPPER, customerKey);
    }

    @Override
    public List<Order> getCustomerOrdersByPhoneNumberSortedByPrice(String customerPhoneNumber) {
        return jdbc.query(GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_PRICE, ORDER_ROW_MAPPER, customerPhoneNumber);
    }

    @Override
    public List<Order> getCustomerOrdersByPhoneNumberSortedByRegDate(String customerPhoneNumber) {
        return jdbc.query(GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_REG_DATE, ORDER_ROW_MAPPER, customerPhoneNumber);
    }

    @Override
    public List<Order> getCustomerOrdersByPhoneNumberSortedByExpDate(String customerPhoneNumber) {
        return jdbc.query(GET_ORDERS_OF_CUSTOMER_BY_PHONE_NUMBER_SORTED_BY_EXP_DATE, ORDER_ROW_MAPPER, customerPhoneNumber);
    }

    @Override
    public List<Customer> getCustomersByPIB(String name, String surname, String secondName) {
        return jdbc.query(GET_CUSTOMERS_BY_PIB, CUSTOMER_ROW_MAPPER, name, surname, secondName);
    }

    @Override
    public List<CustomerWithOrders> getCustomersAndTheirOrdersSortedByName() {
        return jdbc.query(GET_CUSTOMERS_WITH_THEIR_ORDERS, CUSTOMER_WITH_ORDERS_EXTRACTOR);
    }

    @Override
    public List<Customer> getCustomersWithOrdersBetweenDates(Date from, Date to) {
        return jdbc.query(GET_CUSTOMERS_WHO_HAVE_ORDERS_BETWEEN_DATES, CUSTOMER_ROW_MAPPER, from, to);
    }

    @Override
    public List<Customer> getCustomersWhoOrderedSauceWithTypeNumberSortedBySurname(String typeNumber) {
        return jdbc.query(GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_TYPE_NUMBER, CUSTOMER_ROW_MAPPER, typeNumber);
    }

    @Override
    public List<Customer> getCustomersWhoOrderedSauceWithTypeNameSortedBySurname(String typeName) {
        return jdbc.query(GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_TYPE_NAME, CUSTOMER_ROW_MAPPER, typeName);
    }

    @Override
    public List<Customer> getCustomersWhoOrderedSauceWithSauceNumberSortedBySurname(String sauceNumber) {
        return jdbc.query(GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_SAUCE_NUMBER, CUSTOMER_ROW_MAPPER, sauceNumber);
    }

    @Override
    public List<Customer> getCustomersWhoOrderedSauceWithSauceNameSortedBySurname(String sauceName) {
        return jdbc.query(GET_CUSTOMERS_WHO_ORDERED_BATCHES_WITH_SAUCE_NAME, CUSTOMER_ROW_MAPPER, sauceName);
    }

    @Override
    public List<Customer> getCustomerWhoOrderedBatchWithNumber(String batchKey) {
        return jdbc.query(GET_CUSTOMER_WHO_ORDERED_BATCH_NUMBER, CUSTOMER_ROW_MAPPER, batchKey);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbc.update(UPDATE_CUSTOMER,
                customer.getName(),
                customer.getSurname(),
                customer.getPatronymic(),
                customer.getAddress(),
                customer.getNumber());
    }

}