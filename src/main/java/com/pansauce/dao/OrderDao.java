package com.pansauce.dao;

import com.pansauce.model.Batch;
import com.pansauce.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order, String> {

    List<Order> getAllOrdersSortedByRegistrationDate();
    List<Order> getAllOrdersSortedByPrice();

    List<Batch> getAllBatchesOfOrderByNumberSortedByProdDate(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedByPrice(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedByNumber(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedByBatchStatus(String orderNumber);
    List<Batch> getAllBatchesOfOrderByNumberSortedBySauceQuantity(String orderNumber);

}