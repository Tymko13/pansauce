package com.pansauce.repository;

import com.pansauce.dao.GenericDao;
import com.pansauce.model.Sauce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.pansauce.constants.query.SauceQuery.*;
import static com.pansauce.constants.rowMapper.SauceRowMapper.SAUCE_ROW_MAPPER;

@Repository(value = "sauceRepo")
public class SauceRepository implements GenericDao<Sauce> {

    private final JdbcTemplate jdbc;

    @Autowired
    public SauceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Sauce> findAll() {
        return jdbc.query(GET_ALL_SAUCE, SAUCE_ROW_MAPPER);
    }

    @Override
    public void add(Sauce sauce) {
        jdbc.update(ADD_SAUCE,
                sauce.getNumber(),
                sauce.getName(),
                sauce.getType(),
                sauce.getShelfLife(),
                sauce.getWeight(),
                sauce.getCost());
    }
}
