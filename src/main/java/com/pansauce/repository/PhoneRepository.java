package com.pansauce.repository;

import com.pansauce.dao.PhoneDao;
import com.pansauce.model.Phone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.PhoneQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.PHONE_ROW_MAPPER;

@Repository(value = "phoneRepo")
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
    public List<Phone> getCustomerPhonesByKey(String customerKey) {
        return jdbc.query(GET_ALL_PHONES_BY_CUSTOMER_KEY, PHONE_ROW_MAPPER);
    }

}