package com.pansauce.model.dto;

import com.pansauce.model.basic.Phone;

public class PhoneDTO extends Phone {

    private String newPhoneNumber;

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }
}