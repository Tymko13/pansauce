package com.pansauce.service;

import com.pansauce.dao.CustomerDao;
import com.pansauce.exception.batch.NoBatchesFoundException;
import com.pansauce.exception.batch.NonExistingBatchException;
import com.pansauce.exception.customer.InvalidCustomerException;
import com.pansauce.exception.customer.NoCustomersFoundException;
import com.pansauce.exception.customer.NonExistingCustomerException;
import com.pansauce.model.Customer;
import com.pansauce.validator.model.CustomerValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerRepository;

    public CustomerService(
            @Qualifier(value = "customerRepo")
            CustomerDao customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty())
            throw new NoCustomersFoundException();
        return customers;
    }

    public Customer getCustomerByKey(String key) {
        if (!customerRepository.exists(key))
            throw new NonExistingCustomerException();
        return customerRepository.findByKey(key);
    }

    public void addCustomer(Customer customer) {
        CustomerValidator validator = new CustomerValidator();
        List<String> errorMessages = validator.validate(customer);
        if (errorMessages.isEmpty())
            customerRepository.add(customer);
        else throw new InvalidCustomerException(errorMessages);
    }

    public void deleteCustomer(String key) {
        if (customerRepository.exists(key))
            customerRepository.delete(key);
        else throw new NonExistingCustomerException();
    }

}