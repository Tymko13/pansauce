package com.pansauce.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerWithOrders extends Customer {

    private List<Order> orders = new ArrayList<Order>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}