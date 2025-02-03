package com.pansauce.controller;

import com.pansauce.dao.OrderDao;
import com.pansauce.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderDao orderRepository;

    public OrderController(OrderDao orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/order")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/order/{key}")
    public Order getOrderByKey(
            @PathVariable String key
    ) {
        return orderRepository.findByKey(key);
    }

    @PostMapping("/order")
    public void addOrder(
            @RequestBody Order order
    ) {
        orderRepository.add(order);
    }

    @DeleteMapping("/order/{key}")
    public void deleteOrderByKey(
            @PathVariable String key
    ) {
        orderRepository.delete(key);
    }

}
