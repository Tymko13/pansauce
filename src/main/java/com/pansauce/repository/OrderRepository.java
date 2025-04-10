package com.pansauce.repository;

import com.pansauce.dao.OrderDao;
import com.pansauce.model.Batch;
import com.pansauce.model.Order;
import com.pansauce.model.dto.OrderDTO;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.ORDER_KEY_LENGTH;
import static com.pansauce.constants.query.OrderQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.BATCH_ROW_MAPPER;
import static com.pansauce.constants.rowMapper.ModelRowMapper.ORDER_ROW_MAPPER;

@Repository(value = "orderRepo")
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

    @Override
    public List<Order> getAllOrdersSortedByRegDate() {
        return jdbc.query(GET_ALL_ORDERS_SORTED_BY_REG_DATE, ORDER_ROW_MAPPER);
    }

    @Override
    public List<Order> getAllOrdersSortedByPrice() {
        return jdbc.query(GET_ALL_ORDERS_SORTED_BY_PRICE, ORDER_ROW_MAPPER);
    }

    @Override
    public List<Order> getAllOrdersSortedByExpDate() {
        return jdbc.query(GET_ALL_ORDERS_SORTED_BY_EXP_DATE, ORDER_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesOfOrderByNumberSortedByProdDate(String orderNumber) {
        return jdbc.query(GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_PROD_DATE, BATCH_ROW_MAPPER, orderNumber);
    }

    @Override
    public List<Batch> getAllBatchesOfOrderByNumberSortedByPrice(String orderNumber) {
        return jdbc.query(GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_PRICE, BATCH_ROW_MAPPER, orderNumber);
    }

    @Override
    public List<Batch> getAllBatchesOfOrderByNumberSortedByNumber(String orderNumber) {
        return jdbc.query(GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_NUMBER, BATCH_ROW_MAPPER, orderNumber);
    }

    @Override
    public List<Batch> getAllBatchesOfOrderByNumberSortedByBatchStatus(String orderNumber) {
        return jdbc.query(GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_STATUS, BATCH_ROW_MAPPER, orderNumber);
    }

    @Override
    public List<Batch> getAllBatchesOfOrderByNumberSortedBySauceQuantity(String orderNumber) {
        return jdbc.query(GET_BATCHES_OF_ORDER_BY_NUMBER_SORTED_BY_SAUCE_QUANTITY, BATCH_ROW_MAPPER, orderNumber);
    }

    @Override
    public void updateOrder(OrderDTO order) {
        jdbc.update(UPDATE_ORDER,
                order.getExpectedDate(),
                order.getRealDate(),
                order.getDeliveryCost(),
                order.getNumber());
    }

}