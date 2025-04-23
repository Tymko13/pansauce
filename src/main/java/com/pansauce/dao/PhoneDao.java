package com.pansauce.dao;

import com.pansauce.model.basic.Phone;

import java.util.List;

public interface PhoneDao {

    void addCustomerPhone(Phone phone);
    void deleteCustomerPhone(String phoneNumber);
    void deleteAllCustomersPhones(String customerNumber);
    boolean phoneExists(String phoneNumber);

    List<Phone> getCustomerPhonesByKey(String customerKey);

}