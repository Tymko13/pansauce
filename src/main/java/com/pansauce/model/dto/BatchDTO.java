package com.pansauce.model.dto;

import java.math.BigDecimal;

public class BatchDTO {

    private String batchNumber;
    private int quantity;
    private BigDecimal saucePrice;
    private String orderNumber;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
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
        return saucePrice;
    }

    public void setSaucePrice(BigDecimal saucePrice) {
        this.saucePrice = saucePrice;
    }
}