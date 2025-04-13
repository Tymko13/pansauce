package com.pansauce.service;

import com.pansauce.dao.CustomerDao;
import com.pansauce.dao.OrderDao;
import com.pansauce.dao.PhoneDao;
import com.pansauce.exception.customer.InvalidCustomerException;
import com.pansauce.exception.customer.NoCustomersFoundException;
import com.pansauce.exception.customer.NonExistingCustomerException;
import com.pansauce.model.Phone;
import com.pansauce.model.customer.Customer;
import com.pansauce.model.customer.CustomerWithOrders;
import com.pansauce.model.order.Order;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.validator.model.CustomerValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerRepository;
    private final PhoneDao phoneRepository;
    private final OrderDao orderRepository;

    public CustomerService(
            @Qualifier("customerRepo")
            CustomerDao customerRepository,
            @Qualifier("phoneRepo")
            PhoneDao phoneRepository,
            @Qualifier("orderRepo")
            OrderDao orderRepository
    ) {
        this.customerRepository = customerRepository;
        this.phoneRepository = phoneRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderWithCustomerData> getCustomerOrdersByNumberSortedBy(String customerKey, String attribute) {
        return switch (attribute) {
            case "price" -> customerRepository.getCustomerOrdersByKeySortedByPrice(customerKey + "%");
            case "reg_date" -> customerRepository.getCustomerOrdersByKeySortedByRegDate(customerKey + "%");
            case "real_date" -> customerRepository.getCustomerOrdersByKeySortedByExpDate(customerKey + "%");
            default -> new ArrayList<>();
        };
    }

    public List<OrderWithCustomerData> getCustomerOrdersByPhoneSortedBy(String customerPhone, String attribute) {
        return switch (attribute) {
            case "price" -> customerRepository.getCustomerOrdersByPhoneNumberSortedByPrice(customerPhone + "%");
            case "reg_date" -> customerRepository.getCustomerOrdersByPhoneNumberSortedByRegDate(customerPhone + "%");
            case "real_date" -> customerRepository.getCustomerOrdersByPhoneNumberSortedByExpDate(customerPhone + "%");
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

    public void addCustomer(CustomerWithOrders customer) {
        CustomerValidator validator = new CustomerValidator();
        List<String> errorMessages = validator.validate(customer);
        if (!errorMessages.isEmpty())
            throw new InvalidCustomerException(errorMessages);
        customerRepository.add(customer);
        for (String phoneNumber : customer.getPhones()) {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneNumber);
            phone.setCustomerNumber(customer.getNumber());
            phoneRepository.addCustomerPhone(phone);
        }
        Order customerOrder = customer.getOrders().getFirst();
        customerOrder.setCustomerNumber(customer.getNumber());
        orderRepository.add(customerOrder);
    }

    public void updateCustomer(Customer customer) {
        String customerNumber = customer.getNumber();
        if (!customerRepository.exists(customerNumber))
            throw new NonExistingCustomerException();
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(String key) {
        if (customerRepository.exists(key))
            customerRepository.delete(key);
        else throw new NonExistingCustomerException();
    }

}