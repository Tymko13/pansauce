package com.pansauce.repository;

import com.pansauce.dao.OrderDao;
import com.pansauce.model.Order;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.ORDER_KEY_LENGTH;
import static com.pansauce.constants.query.OrderQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.ORDER_ROW_MAPPER;

@Repository
public class OrderRepository implements OrderDao {

    private final JdbcTemplate jdbc;

    public OrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Order order, String orderKey) {
        jdbc.update(ADD_ORDER,
                orderKey,
                order.getRegistrationDate(),
                order.getExpectedDate(),
                order.getRealDate(),
                order.getDeliveryCost(),
                order.getTotalCost());
    }

    @Override
    public void add(Order order) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(ORDER_KEY_LENGTH);
        String orderKey = keyGenerator.nextString();
        insert(order, orderKey);
    }

    @Override
    public List<Order> findAll() {
        return jdbc.query(GET_ALL_ORDERS, ORDER_ROW_MAPPER);
    }

    @Override
    public Order findByKey(String orderKey) {
        List<Order> result = jdbc.query(GET_ORDER_BY_KEY, ORDER_ROW_MAPPER, orderKey);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void delete(String orderKey) {
        jdbc.update(DELETE_ORDER_BY_KEY, orderKey);
    }

    @Override
    public boolean exists(String key) {
        return false;
    }
}
