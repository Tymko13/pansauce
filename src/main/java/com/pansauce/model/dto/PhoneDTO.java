package com.pansauce.model.dto;

import com.pansauce.model.Phone;

public class PhoneDTO extends Phone {

    private String newPhoneNumber;

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }
}