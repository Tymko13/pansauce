package com.pansauce.service;

import com.pansauce.dao.OrderDao;
import com.pansauce.exception.order.*;
import com.pansauce.model.Batch;
import com.pansauce.model.Order;
import com.pansauce.validator.model.OrderValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderDao orderRepository;

    public OrderService(
            @Qualifier(value = "orderRepo")
            OrderDao orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrdersSortedBy(String attribute) {
        return switch (attribute) {
          case "reg_date" -> orderRepository.getAllOrdersSortedByRegistrationDate();
          case "price" -> orderRepository.getAllOrdersSortedByPrice();
          default -> new ArrayList<>();
        };
    }

    public List<Batch> getBatchesOfOrderSortedBy(String attribute, String orderNumber) {
        return switch (attribute) {
            case "price" -> orderRepository.getAllBatchesOfOrderByNumberSortedByPrice(orderNumber + "%");
            case "prod_date" -> orderRepository.getAllBatchesOfOrderByNumberSortedByProdDate(orderNumber + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty())
            throw new NoOrdersFoundException();
        return orders;
    }

    public Order getOrderByKey(String key) {
        validateOrderExistence(key);
        return orderRepository.findByKey(key);
    }

    public void addOrder(Order order) {
        OrderValidator orderValidator = new OrderValidator();
        List<String> errorMessages = orderValidator.validate(order);
        if (errorMessages.isEmpty())
            orderRepository.add(order);
        else throw new InvalidOrderException(errorMessages);
    }

    public void deleteOrder(String key) {
        validateOrderExistence(key);
        orderRepository.delete(key);
    }

    private void validateOrderExistence(String key){
        if (!orderRepository.exists(key))
            throw new NonExistingOrderException();
    }

}
