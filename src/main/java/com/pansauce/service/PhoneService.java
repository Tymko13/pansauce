package com.pansauce.service;

import com.pansauce.dao.CustomerDao;
import com.pansauce.dao.PhoneDao;
import com.pansauce.exception.customer.NonExistingCustomerException;
import com.pansauce.exception.phone.InvalidPhoneException;
import com.pansauce.model.Phone;
import com.pansauce.validator.model.PhoneValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    private final PhoneDao phoneRepository;
    private final CustomerDao customerRepository;

    public PhoneService(
            @Qualifier(value = "phoneRepo")
            PhoneDao phoneRepository,
            @Qualifier(value = "customerRepo")
            CustomerDao customerRepository
    ) {
        this.phoneRepository = phoneRepository;
        this.customerRepository = customerRepository;
    }

    public void addPhoneToCustomer(String customerKey, Phone phone) {
        phone.setCustomerNumber(customerKey);
        PhoneValidator validator = new PhoneValidator();
        List<String> errorMessages = validator.validate(phone);
        if (errorMessages.isEmpty())
            phoneRepository.addCustomerPhone(phone);
        else throw new InvalidPhoneException(errorMessages);
    }

    public List<Phone> getCustomerPhonesByKey(String customerKey) {
        validateCustomerKey(customerKey);
        return phoneRepository.getCustomerPhonesByKey(customerKey);
    }

    public void deletePhoneFromCustomer(String phoneNumber) {
        phoneRepository.deleteCustomerPhone(phoneNumber);
    }

    public boolean phoneExists(String phoneNumber) {
        return phoneRepository.phoneExists(phoneNumber);
    }

    private void validateCustomerKey(String customerKey) {
        if (!customerRepository.exists(customerKey))
            throw new NonExistingCustomerException();
    }

}