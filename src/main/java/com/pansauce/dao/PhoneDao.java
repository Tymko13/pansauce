package com.pansauce.dao;

import com.pansauce.model.Phone;

import java.util.List;

public interface PhoneDao {

    void addCustomerPhone(Phone phone);
    List<Phone> getCustomerPhonesByKey(String customerKey);

}