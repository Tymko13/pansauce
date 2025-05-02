package com.pansauce.service;

import com.pansauce.dao.CustomerDao;
import com.pansauce.dao.OrderDao;
import com.pansauce.dao.PhoneDao;
import com.pansauce.exception.customer.InvalidCustomerException;
import com.pansauce.exception.customer.NoCustomersFoundException;
import com.pansauce.exception.customer.NonExistingCustomerException;
import com.pansauce.model.basic.Batch;
import com.pansauce.model.basic.Phone;
import com.pansauce.model.customer.Customer;
import com.pansauce.model.customer.CustomerOrderData;
import com.pansauce.model.customer.CustomerWithOrders;
import com.pansauce.model.customer.CustomerWithOrdersAndBatches;
import com.pansauce.model.dto.BatchDTO;
import com.pansauce.model.order.Order;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.model.sauce.SauceWithSalesCount;
import com.pansauce.repository.BatchRepository;
import com.pansauce.util.RandomKeyGenerator;
import com.pansauce.validator.model.CustomerValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.CUSTOMER_KEY_LENGTH;
import static com.pansauce.constants.keyLength.KeyLength.ORDER_KEY_LENGTH;

@Service
public class CustomerService {

    private final CustomerDao customerRepository;
    private final PhoneDao phoneRepository;
    private final OrderDao orderRepository;
    private final BatchRepository batchRepo;

    public CustomerService(
            @Qualifier("customerRepo")
            CustomerDao customerRepository,
            @Qualifier("phoneRepo")
            PhoneDao phoneRepository,
            @Qualifier("orderRepo")
            OrderDao orderRepository,
            @Qualifier("batchRepo")
            BatchRepository batchRepo)
    {
        this.customerRepository = customerRepository;
        this.phoneRepository = phoneRepository;
        this.orderRepository = orderRepository;
        this.batchRepo = batchRepo;
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
            case "type_number" -> customerRepository.getCustomersWhoOrderedSauceWithTypeNumberSortedBySurname(value + "%");
            case "type_name" -> customerRepository.getCustomersWhoOrderedSauceWithTypeNameSortedBySurname(value + "%");
            case "sauce_number" -> customerRepository.getCustomersWhoOrderedSauceWithSauceNumberSortedBySurname(value + "%");
            case "sauce_name" -> customerRepository.getCustomersWhoOrderedSauceWithSauceNameSortedBySurname(value+ "%");
            case "batch_number" -> customerRepository.getCustomerWhoOrderedBatchWithNumber(value + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Customer> getCustomersWithNumberStartingWith(String customerNumber) {
        return customerRepository.getCustomersWithNumberStartingWithSortedBySurname(customerNumber + "%");
    }

    public List<Customer> getCustomersWithPhoneStartingWith(String customerPhone) {
        return customerRepository.getCustomerWithPhoneNumberStartingWithSortedBytSurname(customerPhone + "%");
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

    public SauceWithSalesCount getCustomerFavouriteSauceByCustomerKey(String customerKey) {
        return customerRepository.getCustomerFavouriteSauceByCustomerKey(customerKey);
    }

    public List<Customer> getCustomersWhoOrderedAllTypesOfSauce() {
        return customerRepository.getCustomersWhoOrderedAllTypesOfSauce();
    }

    public List<CustomerOrderData> getCustomersOrderData() {
        return customerRepository.getCustomersOrderData();
    }

    public List<Customer> getCustomersWhoOrderedOnlyOneTypeOfSauce() {
        return customerRepository.getCustomersWhoOrderedOnlyOneTypeOfSauce();
    }

    public List<OrderWithCustomerData> getCustomerOrdersBeforeDateWithCustomerKey(String customerKey, Date date) {
        return customerRepository.getOrdersBeforeDateWithCustomerKey(customerKey, date);
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

    public void addCustomer(CustomerWithOrdersAndBatches customer) {
        CustomerValidator validator = new CustomerValidator();
        List<String> errorMessages = validator.validate(customer);
        if (!errorMessages.isEmpty())
            throw new InvalidCustomerException(errorMessages);
        RandomKeyGenerator customerKeyGenerator = new RandomKeyGenerator(CUSTOMER_KEY_LENGTH);
        String customerKey = customerKeyGenerator.nextString();
        customerRepository.insert(customer, customerKey);
        for (String phoneNumber : customer.getPhones()) {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneNumber);
            phone.setCustomerNumber(customerKey);
            phoneRepository.addCustomerPhone(phone);
        }
        Order customerOrder = customer.getOrders().getFirst();
        customerOrder.setCustomerNumber(customerKey);
        RandomKeyGenerator orderKeyGenerator = new RandomKeyGenerator(ORDER_KEY_LENGTH);
        String orderKey = orderKeyGenerator.nextString();
        orderRepository.insert(customerOrder, orderKey);
        for (String batchNumber : customer.getBatchKeys()) {
            Batch batch = batchRepo.findByKey(batchNumber);
            BatchDTO updatedBatch = new BatchDTO();
            updatedBatch.setNumber(batchNumber);
            updatedBatch.setSauceCost(batch.getSauceCost());
            updatedBatch.setQuantity(batch.getQuantity());
            updatedBatch.setOrderNumber(orderKey);
            batchRepo.updateBatch(updatedBatch);
        }
    }

    public void updateCustomer(Customer customer) {
        String customerNumber = customer.getNumber();
        if (!customerRepository.exists(customerNumber))
            throw new NonExistingCustomerException();
        customerRepository.updateCustomer(customer);
        List<String> phones = customer.getPhones();
        phoneRepository.deleteAllCustomersPhones(customerNumber);
        for (String phoneNumber : phones) {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneNumber);
            phone.setCustomerNumber(customer.getNumber());
            phoneRepository.addCustomerPhone(phone);
        }
    }

    public void deleteCustomer(String key) {
        if (customerRepository.exists(key))
            customerRepository.delete(key);
        else throw new NonExistingCustomerException();
    }

}