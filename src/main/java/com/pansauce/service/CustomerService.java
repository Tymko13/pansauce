package com.pansauce.service;

import com.pansauce.dao.CustomerDao;
import com.pansauce.exception.batch.NoBatchesFoundException;
import com.pansauce.exception.batch.NonExistingBatchException;
import com.pansauce.exception.customer.InvalidCustomerException;
import com.pansauce.exception.customer.NoCustomersFoundException;
import com.pansauce.exception.customer.NonExistingCustomerException;
import com.pansauce.model.Customer;
import com.pansauce.model.CustomerWithOrders;
import com.pansauce.model.Order;
import com.pansauce.validator.model.CustomerValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<Order> getCustomerOrdersByNumberSortedBy(String customerKey, String attribute) {
        return switch (attribute) {
            case "price" -> customerRepository.getCustomerOrdersByKeySortedByPrice(customerKey + "%");
            case "reg_date" -> customerRepository.getCustomerOrdersByKeySortedByRegDate(customerKey + "%");
            case "exp_date" -> customerRepository.getCustomerOrdersByKeySortedByExpDate(customerKey + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Order> getCustomerOrdersByPhoneSortedBy(String customerPhone, String attribute) {
        return switch (attribute) {
            case "price" -> customerRepository.getCustomerOrdersByPhoneNumberSortedByPrice(customerPhone + "%");
            case "reg_date" -> customerRepository.getCustomerOrdersByPhoneNumberSortedByRegDate(customerPhone + "%");
            case "exp_date" -> customerRepository.getCustomerOrdersByPhoneNumberSortedByExpDate(customerPhone + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Customer> getCustomersWithOrderThatHasThisAttribute(String attribute, String value) {
        return switch (attribute) {
            case "type_number" -> customerRepository.getCustomersWhoOrderedSauceWithTypeNumberSortedBySurname(value);
            case "type_name" -> customerRepository.getCustomersWhoOrderedSauceWithTypeNameSortedBySurname(value);
            case "sauce_number" -> customerRepository.getCustomersWhoOrderedSauceWithSauceNumberSortedBySurname(value);
            case "sauce_name" -> customerRepository.getCustomersWhoOrderedSauceWithSauceNameSortedBySurname(value);
            case "batch_number" -> customerRepository.getCustomerWhoOrderedBatchWithNumber(value);
            default -> new ArrayList<>();
        };
    }

    public List<Customer> getCustomersByPIB(String name, String surname, String patronymic) {
        return customerRepository.getCustomersByPIB(name + "%", surname + "%", patronymic + "%");
    }

    public List<CustomerWithOrders> getCustomersAndTheirOrdersSortedByName() {
        return customerRepository.getCustomersAndTheirOrdersSortedByName();
    }

    public List<Customer> getCustomersWithOrdersBetweenDates(Date from, Date to) {
        return customerRepository.getCustomersWithOrdersBetweenDates(from, to);
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