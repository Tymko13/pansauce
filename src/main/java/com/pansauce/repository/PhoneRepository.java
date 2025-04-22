package com.pansauce.repository;

import com.pansauce.dao.PhoneDao;
import com.pansauce.model.basic.Phone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.PhoneQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.PHONE_ROW_MAPPER;

@Repository("phoneRepo")
public class PhoneRepository implements PhoneDao {

    private final JdbcTemplate jdbc;

    public PhoneRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public void addCustomerPhone(Phone phone) {
        jdbc.update(ADD_PHONE_TO_CUSTOMER_BY_KEY,
                    phone.getPhoneNumber(),
                    phone.getCustomerNumber());
    }

    @Override
    public void deleteCustomerPhone(String phoneNumber) {
        jdbc.update(DELETE_PHONE_FROM_CUSTOMER, phoneNumber);
    }

    @Override
    public void deleteAllCustomersPhones(String customerNumber) {
        jdbc.update(DELETE_PHONES_FROM_CUSTOMER, customerNumber);
    }

    @Override
    public boolean phoneExists(String phoneNumber) {
        List<String> result = jdbc.queryForList(PHONE_NUMBER_EXISTS, String.class, phoneNumber);
        return !result.isEmpty();
    }

    @Override
    public List<Phone> getCustomerPhonesByKey(String customerKey) {
        return jdbc.query(GET_ALL_PHONES_BY_CUSTOMER_KEY, PHONE_ROW_MAPPER);
    }

}