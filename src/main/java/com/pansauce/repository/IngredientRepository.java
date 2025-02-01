package com.pansauce.repository;

import com.pansauce.dao.IngredientDao;
import com.pansauce.model.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.IngredientQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.INGREDIENT_ROW_MAPPER;

@Repository(value = "ingredientRepo")
public class IngredientRepository implements IngredientDao {

    private final JdbcTemplate jdbc;

    public IngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbc.query(GET_ALL_INGREDIENT, INGREDIENT_ROW_MAPPER);
    }

    @Override
    public Ingredient findByKey(String key) {
        //TODO: Implement method
        return null;
    }

    @Override
    public void add(Ingredient ingredient) {
        //TODO: Implement Validation
        jdbc.update(ADD_INGREDIENT,
                ingredient.getGti(),
                ingredient.getName());
    }

    @Override
    public void delete(String key) {
        //TODO: Implement method
    }

    @Override
    public void update(String key, Ingredient ingredient) {
        //TODO: Implement method
    }
}
