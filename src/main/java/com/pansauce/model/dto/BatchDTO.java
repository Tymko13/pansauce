package com.pansauce.model.dto;

import java.math.BigDecimal;

public class BatchDTO {

    private String number;
    private int quantity;
    private BigDecimal sauceCost;
    private String orderNumber;

    public String getBatchNumber() {
        return number;
    }

    public void setBatchNumber(String number) {
        this.number = number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getSaucePrice() {
        return sauceCost;
    }

    public void setSaucePrice(BigDecimal sauceCost) {
        this.sauceCost = sauceCost;
    }
}