package com.pansauce.repository;

import com.pansauce.dao.IngredientDao;
import com.pansauce.model.Ingredient;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.IngredientQuery.*;
import static com.pansauce.constants.key.KeyLength.INGREDIENT_KEY_LENGTH;
import static com.pansauce.constants.rowMapper.ModelRowMapper.INGREDIENT_ROW_MAPPER;

@Repository(value = "ingredientRepo")
public class IngredientRepository implements IngredientDao {

    private final JdbcTemplate jdbc;
    private final RandomKeyGenerator keyGenerator =
            new RandomKeyGenerator(INGREDIENT_KEY_LENGTH);

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
    public void insert(Ingredient ingredient, String gtiNumber) {
        jdbc.update(ADD_INGREDIENT,
                gtiNumber,
                ingredient.getName());
    }

    @Override
    public void add(Ingredient ingredient) {
        //TODO: Validate Ingredient values
        String gtiNumber = keyGenerator.nextString();
        insert(ingredient, gtiNumber);
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
