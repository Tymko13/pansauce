package com.pansauce.model.dto;

import java.math.BigDecimal;

public class BatchDTO {

    private String number;
    private int quantity;
    private BigDecimal sauceCost;
    private String orderNumber;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSauceCost() {
        return sauceCost;
    }

    public void setSauceCost(BigDecimal sauceCost) {
        this.sauceCost = sauceCost;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}