package com.pansauce.model.order;

import java.util.List;

public class OrderWithCustomerData extends Order {

    private String customerName;
    private String customerSurname;
    private String customerPatronymic;
    private List<String> customerPhoneNumbers;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerPatronymic() {
        return customerPatronymic;
    }

    public void setCustomerPatronymic(String customerPatronymic) {
        this.customerPatronymic = customerPatronymic;
    }

    public List<String> getCustomerPhoneNumbers() {
        return customerPhoneNumbers;
    }

    public void setCustomerPhoneNumbers(List<String> customerPhoneNumbers) {
        this.customerPhoneNumbers = customerPhoneNumbers;
    }
}