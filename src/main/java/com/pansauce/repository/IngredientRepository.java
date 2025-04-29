package com.pansauce.repository;

import com.pansauce.dao.IngredientDao;
import com.pansauce.exception.ingredient.NonExistingIngredientException;
import com.pansauce.model.basic.Ingredient;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.IngredientQuery.*;
import static com.pansauce.constants.keyLength.KeyLength.INGREDIENT_KEY_LENGTH;
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
        List<Ingredient> result = jdbc.query(GET_INGREDIENT_BY_KEY, INGREDIENT_ROW_MAPPER, key);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void insert(Ingredient ingredient, String gtiNumber) {
        jdbc.update(ADD_INGREDIENT, gtiNumber, ingredient.getName());
    }

    @Override
    public void add(Ingredient ingredient) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(INGREDIENT_KEY_LENGTH);
        String gtiNumber = keyGenerator.nextString();
        insert(ingredient, gtiNumber);
    }

    @Override
    public void delete(String key) {
        jdbc.update(DELETE_INGREDIENT_BY_KEY, key);
    }

    @Override
    public boolean exists(String key) {
        return findByKey(key) != null;
    }

    @Override
    public List<Ingredient> getAllIngredientsSortedByNumber() {
        return jdbc.query(GET_ALL_INGREDIENTS_SORTED_BY_NUMBER, INGREDIENT_ROW_MAPPER);
    }

    @Override
    public List<Ingredient> getAllIngredientsSortedByName() {
        return jdbc.query(GET_ALL_INGREDIENTS_SORTED_BY_NAME, INGREDIENT_ROW_MAPPER);
    }

    @Override
    public List<Ingredient> getIngredientsWithNameStartingWithSortedByNumber(String prefix) {
        return jdbc.query(GET_INGREDIENTS_WITH_NAME_STARTING_WITH_SORTED_BY_NUMBER, INGREDIENT_ROW_MAPPER, prefix);
    }

    @Override
    public List<Ingredient> getIngredientsWithNameStartingWithSortedByName(String prefix) {
        return jdbc.query(GET_INGREDIENTS_WITH_NAME_STARTING_WITH_SORTED_BY_NAME, INGREDIENT_ROW_MAPPER, prefix);
    }

    @Override
    public List<Ingredient> getIngredientWithNumberStartingWithSortedByNumber(String prefix) {
        return jdbc.query(GET_INGREDIENTS_WITH_NUMBER_STARTING_WITH_SORTED_BY_NUMBER, INGREDIENT_ROW_MAPPER, prefix);
    }

    @Override
    public List<Ingredient> getIngredientWithNumberStartingWithSortedByName(String prefix) {
        return jdbc.query(GET_INGREDIENTS_WITH_NUMBER_STARTING_WITH_SORTED_BY_NAME, INGREDIENT_ROW_MAPPER, prefix);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        if (!exists(ingredient.getGti()))
            throw new NonExistingIngredientException();
        jdbc.update(UPDATE_INGREDIENT_WITH_NUMBER, ingredient.getName(), ingredient.getGti());
    }

}