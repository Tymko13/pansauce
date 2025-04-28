package com.pansauce.dao;

import com.pansauce.model.customer.Customer;
import com.pansauce.model.customer.CustomerOrderData;
import com.pansauce.model.customer.CustomerWithOrders;
import com.pansauce.model.order.OrderWithCustomerData;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.sauce.SauceWithSalesCount;

import java.util.Date;
import java.util.List;

public interface CustomerDao extends GenericDao<Customer, String> {

    List<OrderWithCustomerData> getCustomerOrdersByKeySortedByPrice(String customerKey);
    List<OrderWithCustomerData> getCustomerOrdersByKeySortedByRegDate(String customerKey);
    List<OrderWithCustomerData> getCustomerOrdersByKeySortedByExpDate(String customerKey);

    List<OrderWithCustomerData> getCustomerOrdersByPhoneNumberSortedByPrice(String customerPhoneNumber);
    List<OrderWithCustomerData> getCustomerOrdersByPhoneNumberSortedByRegDate(String customerPhoneNumber);
    List<OrderWithCustomerData> getCustomerOrdersByPhoneNumberSortedByExpDate(String customerPhoneNumber);

    List<Customer> getCustomersByPIB(String name, String surname, String secondName);

    List<Customer> getCustomersWithNumberStartingWithSortedBySurname(String customerNumber);
    List<Customer> getCustomerWithPhoneNumberStartingWithSortedBytSurname(String customerPhoneNumber);

    List<CustomerWithOrders> getCustomersAndTheirOrdersSortedByName();

    List<Customer> getCustomersWithOrdersBetweenDates(Date from, Date to);

    List<Customer> getCustomersWhoOrderedSauceWithTypeNumberSortedBySurname(String typeNumber);
    List<Customer> getCustomersWhoOrderedSauceWithTypeNameSortedBySurname(String typeName);
    List<Customer> getCustomersWhoOrderedSauceWithSauceNumberSortedBySurname(String sauceNumber);
    List<Customer> getCustomersWhoOrderedSauceWithSauceNameSortedBySurname(String sauceName);
    List<Customer> getCustomerWhoOrderedBatchWithNumber(String batchKey);

    SauceWithSalesCount getCustomerFavouriteSauceByCustomerKey(String customerKey);
    List<Customer> getCustomersWhoOrderedAllTypesOfSauce();

    List<CustomerOrderData> getCustomersOrderData();

    void updateCustomer(Customer customer);

}