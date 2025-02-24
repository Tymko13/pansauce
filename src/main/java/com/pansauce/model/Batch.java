package com.pansauce.model;

import java.math.BigDecimal;
import java.util.Date;

public class Batch {

    private String number;
    private int quantity;
    private Date productionDate;
    private Date expirationDate;
    private BigDecimal sauceCost;
    private BigDecimal cost;
    private String status;
    private String sauceNumber;
    private String orderNumber;

    public String getNumber() {
        return number;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public String getSauceNumber() {
        return sauceNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getSauceCost() {
        return sauceCost;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSauceNumber(String sauceNumber) {
        this.sauceNumber = sauceNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setSauceCost(BigDecimal sauceCost) {
        this.sauceCost = sauceCost;
    }
}