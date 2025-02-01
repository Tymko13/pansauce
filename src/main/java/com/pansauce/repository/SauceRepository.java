package com.pansauce.repository;

import com.pansauce.dao.SauceDao;
import com.pansauce.model.Sauce;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.pansauce.constants.key.KeyLength.INGREDIENT_KEY_LENGTH;
import static com.pansauce.constants.key.KeyLength.SAUCE_KEY_LENGTH;
import static com.pansauce.constants.query.SauceQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.SAUCE_ROW_MAPPER;

@Repository(value = "sauceRepo")
public class SauceRepository implements SauceDao {

    private final JdbcTemplate jdbc;
    private final RandomKeyGenerator keyGenerator =
            new RandomKeyGenerator(SAUCE_KEY_LENGTH);

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
    public void insert(Sauce sauce, String sauceNumber) {
        jdbc.update(ADD_SAUCE,
                sauceNumber,
                sauce.getName(),
                sauce.getType(),
                sauce.getShelfLife(),
                sauce.getWeight(),
                sauce.getCost());
    }

    @Override
    public void add(Sauce sauce) {
        //TODO: Implement Validation
        String sauceNumber = keyGenerator.nextString();
        insert(sauce, sauceNumber);
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
