package com.pansauce.dao;

import com.pansauce.model.Phone;
import com.pansauce.model.dto.PhoneDTO;

import java.util.List;

public interface PhoneDao {

    void addCustomerPhone(Phone phone);
    void deleteCustomerPhone(String phoneNumber);

    List<Phone> getCustomerPhonesByKey(String customerKey);

}