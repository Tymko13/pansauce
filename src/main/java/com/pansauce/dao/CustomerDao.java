package com.pansauce.dao;

import com.pansauce.model.Customer;
import com.pansauce.model.CustomerWithOrders;
import com.pansauce.model.Order;

import java.util.Date;
import java.util.List;

public interface CustomerDao extends GenericDao<Customer, String> {

    List<Order> getCustomerOrdersByKeySortedByPrice(String customerKey);
    List<Order> getCustomerOrdersByKeySortedByRegDate(String customerKey);
    List<Order> getCustomerOrdersByKeySortedByExpDate(String customerKey);

    List<Order> getCustomerOrdersByPhoneNumberSortedByPrice(String customerPhoneNumber);
    List<Order> getCustomerOrdersByPhoneNumberSortedByRegDate(String customerPhoneNumber);
    List<Order> getCustomerOrdersByPhoneNumberSortedByExpDate(String customerPhoneNumber);

    List<Customer> getCustomersByPIB(String name, String surname, String secondName);

    List<CustomerWithOrders> getCustomersAndTheirOrdersSortedByName();

    List<Customer> getCustomersWithOrdersBetweenDates(Date from, Date to);

    List<Customer> getCustomersWhoOrderedSauceWithTypeNumberSortedBySurname(String typeNumber);
    List<Customer> getCustomersWhoOrderedSauceWithTypeNameSortedBySurname(String typeName);
    List<Customer> getCustomersWhoOrderedSauceWithSauceNumberSortedBySurname(String sauceNumber);
    List<Customer> getCustomersWhoOrderedSauceWithSauceNameSortedBySurname(String sauceName);
    List<Customer> getCustomerWhoOrderedBatchWithNumber(String batchKey);

}