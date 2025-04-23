package com.pansauce.repository;

import com.pansauce.dao.SauceIngredientDao;
import com.pansauce.model.basic.SauceIngredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.SauceQuery.ADD_INGREDIENT_TO_SAUCE_BY_KEY;
import static com.pansauce.constants.query.SauceQuery.GET_ALL_SAUCE_INGREDIENTS;
import static com.pansauce.constants.rowMapper.ModelRowMapper.SAUCE_INGREDIENT_ROW_MAPPER;

@Repository(value = "sauceIngredientRepo")
public class SauceIngredientRepository implements SauceIngredientDao {

    private final JdbcTemplate jdbc;

    public SauceIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<SauceIngredient> findAllSauceIngredientsByKey(String sauceKey) {
        return jdbc.query(GET_ALL_SAUCE_INGREDIENTS,
                          SAUCE_INGREDIENT_ROW_MAPPER,
                          sauceKey);
    }

    @Override
    public void addSauceIngredientByKey(String sauceKey, SauceIngredient sauceIngredient) {
        jdbc.update(ADD_INGREDIENT_TO_SAUCE_BY_KEY,
                    sauceKey,
                    sauceIngredient.getGti(),
                    sauceIngredient.getWeight());
    }

}