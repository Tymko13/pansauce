package com.pansauce.repository;

import com.pansauce.dao.SauceDao;
import com.pansauce.model.*;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.SAUCE_KEY_LENGTH;
import static com.pansauce.constants.query.SauceQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.*;

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
        List<Sauce> result = jdbc.query(GET_SAUCE_BY_KEY,
                SAUCE_ROW_MAPPER,
                key);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void insert(Sauce sauce, String sauceNumber) {
        jdbc.update(ADD_SAUCE,
                sauceNumber,
                sauce.getName(),
                sauce.getShelfLife(),
                sauce.getWeight(),
                sauce.getCost(),
                sauce.getTypeNumber());
    }

    @Override
    public void add(Sauce sauce) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(SAUCE_KEY_LENGTH);
        String sauceNumber = keyGenerator.nextString();
        insert(sauce, sauceNumber);
    }

    @Override
    public void delete(String key) {
        jdbc.update(DELETE_SAUCE_BY_KEY, key);
    }

    @Override
    public boolean exists(String key) {
        return findByKey(key) != null;
    }

}