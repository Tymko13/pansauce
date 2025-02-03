package com.pansauce.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String number;
    private Date registrationDate;
    private Date expectedDate;
    private Date realDate;
    private BigDecimal deliveryCost;
    private BigDecimal totalCost;


    public String getNumber() {
        return number;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public Date getRealDate() {
        return realDate;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public void setRealDate(Date realDate) {
        this.realDate = realDate;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

}
