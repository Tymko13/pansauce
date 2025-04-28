package com.pansauce.model.customer;

import java.math.BigDecimal;

public class CustomerOrderData {
    private String customerNumber;
    private String customerSurname;
    private int totalOrdersCount;
    private BigDecimal totalOrdersPrice;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public void setTotalOrdersCount(int totalOrdersCount) {
        this.totalOrdersCount = totalOrdersCount;
    }

    public BigDecimal getTotalOrdersPrice() {
        return totalOrdersPrice;
    }

    public void setTotalOrdersPrice(BigDecimal totalOrdersPrice) {
        this.totalOrdersPrice = totalOrdersPrice;
    }
}