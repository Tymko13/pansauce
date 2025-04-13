package com.pansauce.controller;

import com.pansauce.model.Phone;
import com.pansauce.model.dto.PhoneDTO;
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

    @PostMapping("/customer/{key}/phone")
    public void addPhoneToCustomer(
            @PathVariable String key,
            @RequestBody Phone phone
    ) {
        phoneService.addPhoneToCustomer(key, phone);
    }

    @GetMapping("/customer/{key}/phone")
    public List<Phone> getCustomerPhones(
            @PathVariable String key
    ) {
        return phoneService.getCustomerPhonesByKey(key);
    }

    @DeleteMapping("/phone/{key}")
    public void deletePhoneFromCustomer(
            @PathVariable String key
    ) {
        phoneService.deletePhoneFromCustomer(key);
    }

}