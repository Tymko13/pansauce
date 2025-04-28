package com.pansauce.dao;

import com.pansauce.model.basic.Batch;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.sauce.SauceWithIncome;
import com.pansauce.model.sauce.SauceWithRecipe;
import com.pansauce.model.sauce.SauceWithSalesCount;

import java.util.List;

public interface SauceDao extends GenericDao<Sauce, String> {

    List<Sauce> getSaucesWithNameStartingWithSortedByName(String prefix);
    List<Sauce> getSaucesWithNameStartingWithSortedByNumber(String prefix);
    List<Sauce> getSaucesWithNameStartingWithSortedByTypeName(String prefix);
    List<Sauce> getSaucesWithNameStartingWithSortedByPrice(String prefix);

    List<Sauce> getSaucesWithNumberStartingWithSortedByName(String prefix);
    List<Sauce> getSaucesWithNumberStartingWithSortedByNumber(String prefix);
    List<Sauce> getSaucesWithNumberStartingWithSortedByTypeName(String prefix);
    List<Sauce> getSaucesWithNumberStartingWithSortedByPrice(String prefix);

    List<Sauce> getAllSaucesSortedByName();
    List<Sauce> getAllSaucesSortedByTypeName();
    List<Sauce> getAllSaucesSortedByNumber();
    List<Sauce> getAllSaucesSortedByPrice();

    List<Batch> getAllBatchesOfSauceSortedByStatusBySauceKey(String sauceKey);
    List<Batch> getAllBatchesOfSauceSortedByPriceBySauceKey(String sauceKey);
    List<Batch> getAllBatchesOfSauceSortedByProdDateBySauceKey(String sauceKey);
    List<Batch> getAllBatchesOfSauceSortedBySauceQuantityBySauceKey(String sauceKey);
    List<Batch> getAllBatchesOfSauceSortedByNumberBySauceKey(String sauceKey);

    List<Batch> getAllBatchesOfSauceSortedByStatusBySauceName(String sauceName);
    List<Batch> getAllBatchesOfSauceSortedByPriceBySauceName(String sauceName);
    List<Batch> getAllBatchesOfSauceSortedByProdDateBySauceName(String sauceName);
    List<Batch> getAllBatchesOfSauceSortedBySauceQuantityBySauceName(String sauceName);
    List<Batch> getAllBatchesOfSauceSortedByNumberBySauceName(String sauceName);

    List<SauceWithRecipe> getAllSaucesWithRecipeSortedByName();
    List<SauceWithRecipe> getAllSaucesWithRecipeSortedByWeight();

    List<SauceWithIncome> getBestFiveSaucesByIncome();
    List<SauceWithSalesCount> getBestFiveSaucesBySales();

    List<SauceWithIncome> getWorstFiveSaucesByIncome();
    List<SauceWithSalesCount> getWorstFiveSaucesBySales();

    List<SauceWithRecipe> getBestFiveSaucesWithRecipeByIncome();
    List<SauceWithRecipe> getBestFiveSaucesWithRecipeBySales();

    List<SauceWithRecipe> getWorstFiveSaucesWithRecipeByIncome();
    List<SauceWithRecipe> getWorstFiveSaucesWithRecipeBySales();

    List<Sauce> getSaucesWithoutIngredientAndWithoutType(String ingredientNumber, String typeNumber);
    List<Sauce> getSauceThatContainsRecipeOfSauceWithKey(String sauceKey);

    void updateSauce(SauceWithRecipe sauce);
    void deleteSauceIngredients(String sauceKey);

}