package com.pansauce.model.customer;

import com.pansauce.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerWithOrders extends Customer {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}