package com.pansauce.dao;

import com.pansauce.model.Batch;
import com.pansauce.model.dto.OrderDTO;
import com.pansauce.model.order.Order;
import com.pansauce.model.order.OrderWithCustomerData;

import java.util.List;

public interface OrderDao extends GenericDao<OrderWithCustomerData, String> {

    List<OrderWithCustomerData> getAllOrdersSortedByRegDate();
    List<OrderWithCustomerData> getAllOrdersSortedByPrice();
    List<OrderWithCustomerData> getAllOrdersSortedByExpDate();

    List<Batch> getAllBatchesOfOrderByNumberSortedByProdDate(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedByPrice(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedByNumber(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedByBatchStatus(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedBySauceQuantity(String orderNumber);

    void updateOrder(OrderDTO order);
    void insert(Order order, String orderKey);
    void add(Order order);

}