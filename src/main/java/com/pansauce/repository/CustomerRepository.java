package com.pansauce.repository;

import com.pansauce.dao.CustomerDao;
import com.pansauce.model.Customer;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.CUSTOMER_KEY_LENGTH;
import static com.pansauce.constants.query.CustomerQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.CUSTOMER_ROW_MAPPER;

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

}