package com.pansauce.controller;

import com.pansauce.model.Customer;
import com.pansauce.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<Customer> getBatches() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{key}")
    public Customer getBatchByKey(
            @PathVariable String key
    ) {
        return customerService.getCustomerByKey(key);
    }

    @PostMapping("/customer")
    public void addBatch(
            @RequestBody Customer customer
    ) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/customer/{key}")
    public void deleteBatch(
            @PathVariable String key
    ) {
        customerService.deleteCustomer(key);
    }

}