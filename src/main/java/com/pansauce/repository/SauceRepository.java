package com.pansauce.repository;

import com.pansauce.dao.SauceDao;
import com.pansauce.model.*;
import com.pansauce.model.sauce.*;
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

    @Override
    public List<Sauce> getSaucesWithNameStartingWith(String prefix) {
        return jdbc.query(GET_SAUCE_BY_NAME_PREFIX, SAUCE_ROW_MAPPER, prefix);
    }

    @Override
    public List<Sauce> getSaucesWithNumberStartingWith(String prefix) {
        return jdbc.query(GET_SAUCE_BY_NUMBER_PREFIX, SAUCE_ROW_MAPPER, prefix);
    }

    @Override
    public List<Sauce> getAllSaucesSortedByName() {
        return jdbc.query(GET_ALL_SAUCES_SORTED_BY_NAME, SAUCE_ROW_MAPPER);
    }

    @Override
    public List<Sauce> getAllSaucesSortedByType() {
        return jdbc.query(GET_ALL_SAUCES_SORTED_BY_TYPE_NAME, SAUCE_ROW_MAPPER);
    }

    @Override
    public List<Sauce> getAllSaucesSortedByNumber() {
        return jdbc.query(GET_ALL_SAUCES_SORTED_BY_NUMBER, SAUCE_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByStatusBySauceKey(String sauceKey) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_STATUS, BATCH_ROW_MAPPER, sauceKey);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByPriceBySauceKey(String sauceKey) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_PRICE, BATCH_ROW_MAPPER, sauceKey);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByProdDateBySauceKey(String sauceKey) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_PROD_DATE, BATCH_ROW_MAPPER, sauceKey);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedBySauceQuantityBySauceKey(String sauceKey) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_SAUCE_QUANTITY, BATCH_ROW_MAPPER, sauceKey);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByNumberBySauceKey(String sauceKey) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NUMBER_SORTED_BY_NUMBER, BATCH_ROW_MAPPER, sauceKey);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByStatusBySauceName(String sauceName) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_STATUS, BATCH_ROW_MAPPER, sauceName);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByPriceBySauceName(String sauceName) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_PRICE, BATCH_ROW_MAPPER, sauceName);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByProdDateBySauceName(String sauceName) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_PROD_DATE, BATCH_ROW_MAPPER, sauceName);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedBySauceQuantityBySauceName(String sauceName) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_SAUCE_QUANTITY, BATCH_ROW_MAPPER, sauceName);
    }

    @Override
    public List<Batch> getAllBatchesOfSauceSortedByNumberBySauceName(String sauceName) {
        return jdbc.query(GET_ALL_BATCHES_OF_SAUCE_BY_NAME_SORTED_BY_NUMBER, BATCH_ROW_MAPPER, sauceName);
    }

    @Override
    public List<SauceWithRecipe> getAllSaucesWithRecipeSortedByName() {
        return jdbc.query(GET_ALL_SAUCE_RECIPE_SORTED_BY_NAME, SAUCE_WITH_RECIPE_EXTRACTOR);
    }

    @Override
    public List<SauceWithRecipe> getAllSaucesWithRecipeSortedByWeight() {
        return jdbc.query(GET_ALL_SAUCE_RECIPE_SORTED_BY_WEIGHT, SAUCE_WITH_RECIPE_EXTRACTOR);
    }

    @Override
    public List<SauceWithIncome> getBestFiveSaucesByIncome() {
        return jdbc.query(GET_TOP_FIVE_SAUCE_BY_INCOME, SAUCE_WITH_INCOME_ROW_MAPPER);
    }

    @Override
    public List<SauceWithSalesCount> getBestFiveSaucesBySales() {
        return jdbc.query(GET_TOP_FIVE_SAUCE_BY_SALES_COUNT, SAUCE_WITH_SALES_COUNT_ROW_MAPPER);
    }

    @Override
    public List<SauceWithIncome> getWorstFiveSaucesByIncome() {
        return jdbc.query(GET_LAST_FIVE_SAUCE_BY_INCOME, SAUCE_WITH_INCOME_ROW_MAPPER);
    }

    @Override
    public List<SauceWithSalesCount> getWorstFiveSaucesBySales() {
        return jdbc.query(GET_LAST_FIVE_SAUCE_BY_SALES_COUNT, SAUCE_WITH_SALES_COUNT_ROW_MAPPER);
    }

    @Override
    public List<SauceWithRecipe> getBestFiveSaucesWithRecipeByIncome() {
        return jdbc.query(GET_TOP_FIVE_SAUCE_RECIPE_BY_INCOME, SAUCE_WITH_RECIPE_EXTRACTOR);
    }

    @Override
    public List<SauceWithRecipe> getBestFiveSaucesWithRecipeBySales() {
        return jdbc.query(GET_TOP_FIVE_SAUCE_RECIPE_BY_SALES_COUNT, SAUCE_WITH_RECIPE_EXTRACTOR);
    }

    @Override
    public List<SauceWithRecipe> getWorstFiveSaucesWithRecipeByIncome() {
        return jdbc.query(GET_LAST_FIVE_SAUCE_RECIPE_BY_INCOME, SAUCE_WITH_RECIPE_EXTRACTOR);
    }

    @Override
    public List<SauceWithRecipe> getWorstFiveSaucesWithRecipeBySales() {
        return jdbc.query(GET_LAST_FIVE_SAUCE_RECIPE_BY_SALES_COUNT, SAUCE_WITH_RECIPE_EXTRACTOR);
    }
}