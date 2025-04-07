package com.pansauce.controller;

import com.pansauce.model.Batch;
import com.pansauce.model.Order;
import com.pansauce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/order", params = {"sorted"})
    public List<Order> getAllOrdersSortedBy(
        @RequestParam("sorted") String attribute
    ) {
        return orderService.getAllOrdersSortedBy(attribute);
    }

    @GetMapping(value = "/order", params = {"number", "sorted"})
    public List<Batch> getBatchesOfOrderSortedBy(
            @RequestParam("number") String orderNumber,
            @RequestParam("sorted") String attribute
    ) {
        return orderService.getBatchesOfOrderSortedBy(attribute, orderNumber);
    }

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{key}")
    public Order getOrderByKey(
            @PathVariable String key
    ) {
        return orderService.getOrderByKey(key);
    }

    @PostMapping("/order")
    public void addOrder(
            @RequestBody Order order
    ) {
        orderService.addOrder(order);
    }

    @DeleteMapping("/order/{key}")
    public void deleteOrderByKey(
            @PathVariable String key
    ) {
        orderService.deleteOrder(key);
    }

}
