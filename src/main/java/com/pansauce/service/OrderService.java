package com.pansauce.service;

import com.pansauce.dao.BatchDao;
import com.pansauce.dao.OrderDao;
import com.pansauce.exception.batch.NonExistingBatchException;
import com.pansauce.exception.order.*;
import com.pansauce.model.Batch;
import com.pansauce.model.dto.BatchDTO;
import com.pansauce.model.order.Order;
import com.pansauce.model.dto.OrderDTO;
import com.pansauce.model.order.OrderWithBatchKeys;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.validator.model.OrderValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderDao orderRepository;
    private final BatchDao batchRepository;

    public OrderService(
            @Qualifier(value = "orderRepo")
            OrderDao orderRepository,
            @Qualifier(value = "batchRepo")
            BatchDao batchRepository
    ) {
        this.orderRepository = orderRepository;
        this.batchRepository = batchRepository;
    }

    public List<OrderWithCustomerData> getAllOrdersSortedBy(String attribute) {
        return switch (attribute) {
          case "reg_date" -> orderRepository.getAllOrdersSortedByRegDate();
          case "price" -> orderRepository.getAllOrdersSortedByPrice();
          case "real_date" -> orderRepository.getAllOrdersSortedByExpDate();
          default -> new ArrayList<>();
        };
    }

    public List<OrderWithCustomerData> getOrdersWithNumberStartingWithSortedBy(String prefix, String attribute) {
        return switch (attribute) {
            case "reg_date" -> orderRepository.getOrdersSortedByRegDateWithNumberStartingWith(prefix + "%");
            case "price" -> orderRepository.getOrdersSortedByPriceWithNumberStartingWith(prefix + "%");
            case "real_date" -> orderRepository.getOrdersSortedByExpDateWithNumberStartingWith(prefix + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Batch> getBatchesOfOrderSortedBy(String attribute, String orderNumber) {
        return switch (attribute) {
            case "price" -> orderRepository.getAllBatchesOfOrderByNumberSortedByPrice(orderNumber + "%");
            case "prod_date" -> orderRepository.getAllBatchesOfOrderByNumberSortedByProdDate(orderNumber + "%");
            case "status" -> orderRepository.getAllBatchesOfOrderByNumberSortedByBatchStatus(orderNumber + "%");
            case "sauce_quantity" -> orderRepository.getAllBatchesOfOrderByNumberSortedBySauceQuantity(orderNumber + "%");
            case "number" -> orderRepository.getAllBatchesOfOrderByNumberSortedByNumber(orderNumber + "%");
            default -> new ArrayList<>();
        };
    }

    public List<OrderWithCustomerData> getAllOrders() {
        List<OrderWithCustomerData> orders = orderRepository.findAll();
        if (orders.isEmpty())
            throw new NoOrdersFoundException();
        return orders;
    }

    public Order getOrderByKey(String key) {
        validateOrderExistence(key);
        return orderRepository.findByKey(key);
    }

    public void addOrder(OrderWithBatchKeys order) {
        orderRepository.add(order);
        for (String batchKey : order.getBatchKeys()) {
            if (!batchRepository.exists(batchKey))
                throw new NonExistingBatchException();
            BatchDTO batch = new BatchDTO();
            batch.setNumber(batchKey);
            batch.setOrderNumber(order.getNumber());
            batchRepository.updateBatch(batch);
        }
    }

    public void deleteOrder(String key) {
        throw new UnsupportedOperationException();
//        validateOrderExistence(key);
//        orderRepository.delete(key);
    }

    public void updateOrder(OrderDTO order) {
        String orderNumber = order.getNumber();
        validateOrderExistence(orderNumber);
        orderRepository.updateOrder(order);
    }

    private void validateOrderExistence(String key){
        if (!orderRepository.exists(key))
            throw new NonExistingOrderException();
    }

}
