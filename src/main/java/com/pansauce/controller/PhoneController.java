package com.pansauce.controller;

import com.pansauce.model.Phone;
import com.pansauce.service.PhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(
            PhoneService phoneService
    ) {
        this.phoneService = phoneService;
    }

    @PostMapping("/{customer}")
    public void addPhoneToCustomer(
            @PathVariable String customer,
            @RequestBody Phone phone
    ) {
        phoneService.addPhoneToCustomer(customer, phone);
    }

    @GetMapping("/{customer}")
    public List<Phone> getCustomerPhones(
            @PathVariable String customer
    ) {
        return phoneService.getCustomerPhonesByKey(customer);
    }

}