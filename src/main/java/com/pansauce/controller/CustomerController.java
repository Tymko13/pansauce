package com.pansauce.controller;

import com.pansauce.model.customer.Customer;
import com.pansauce.model.customer.CustomerWithOrders;
import com.pansauce.model.customer.CustomerWithOrdersAndBatches;
import com.pansauce.model.order.Order;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.service.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customer", params = {"number", "sorted"})
    public List<OrderWithCustomerData> getCustomerOrdersByNumberSortedBy(
            @RequestParam("number") String customerKey,
            @RequestParam("sorted") String attribute
    ) {
        return customerService.getCustomerOrdersByNumberSortedBy(customerKey, attribute);
    }

    @GetMapping(value = "/customer", params = {"phone", "sorted"})
    public List<OrderWithCustomerData> getCustomerOrdersByPhoneSortedBy(
            @RequestParam("phone") String customerPhone,
            @RequestParam("sorted") String attribute
    ) {
        return customerService.getCustomerOrdersByPhoneSortedBy(customerPhone, attribute);
    }

    @GetMapping(value = "/customer/search", params = {"name", "surname", "patronymic"})
    public List<Customer> getCustomersByPIB(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "surname", defaultValue = "") String surname,
            @RequestParam(value = "patronymic", defaultValue = "") String patronymic
    ) {
        return customerService.getCustomersByPIB(name, surname, patronymic);
    }

    @GetMapping(value = "/customer/search", params = {"number"})
    public List<Customer> getCustomersWithNumberStartingWith(
            @RequestParam("number") String customerNumber
    ) {
        return customerService.getCustomersWithNumberStartingWith(customerNumber);
    }

    @GetMapping(value = "/customer/search", params = {"phone"})
    public List<Customer> getCustomersWithPhoneStartingWith(
            @RequestParam("phone") String customerPhone
    ) {
        return customerService.getCustomersWithPhoneStartingWith(customerPhone);
    }

    @GetMapping("/customer/order")
    public List<CustomerWithOrders> getCustomersAndTheirOrders() {
        return customerService.getCustomersAndTheirOrdersSortedByName();
    }

    @GetMapping(value = "/customer", params = {"from", "to"})
    public List<Customer> getCustomersWithOrdersBetweenDates(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
    ) {
        return customerService.getCustomersWithOrdersBetweenDates(from, to);
    }

    @GetMapping(value = "/customer", params = {"attribute", "value"})
    public List<Customer> getCustomersWithOrderThatHasThisAttribute(
            @RequestParam("attribute") String attribute,
            @RequestParam("value") String value
    ) {
        return customerService.getCustomersWithOrderThatHasThisAttribute(attribute, value);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{key}")
    public Customer getCustomerByKey(
            @PathVariable String key
    ) {
        return customerService.getCustomerByKey(key);
    }

    @PostMapping("/customer")
    public void addCustomer(
            @RequestBody CustomerWithOrdersAndBatches customer
    ) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/customer/{key}")
    public void deleteCustomer(
            @PathVariable String key
    ) {
        customerService.deleteCustomer(key);
    }

    @PatchMapping("/customer")
    public void updateCustomer(
            @RequestBody Customer customer
    ) {
        customerService.updateCustomer(customer);
    }

}