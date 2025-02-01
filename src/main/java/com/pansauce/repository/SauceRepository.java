package com.pansauce.repository;

import com.pansauce.dao.SauceDao;
import com.pansauce.model.Sauce;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.pansauce.constants.query.SauceQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.SAUCE_ROW_MAPPER;

@Repository(value = "sauceRepo")
public class SauceRepository implements SauceDao {

    private final JdbcTemplate jdbc;

    public SauceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Sauce> findAll() {
        return jdbc.query(GET_ALL_SAUCE, SAUCE_ROW_MAPPER);
    }

    @Override
    public Sauce findByKey(String key) {
        //TODO: Implement method
        return null;
    }

    @Override
    public void add(Sauce sauce) {
        //TODO: Implement Validation
        jdbc.update(ADD_SAUCE,
                sauce.getNumber(),
                sauce.getName(),
                sauce.getType(),
                sauce.getShelfLife(),
                sauce.getWeight(),
                sauce.getCost());
    }

    @Override
    public void delete(String key) {
        //TODO: Implement method
    }

    @Override
    public void update(String key, Sauce sauce) {
        //TODO: Implement method
    }
}
