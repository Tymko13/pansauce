package com.pansauce.dao;

import com.pansauce.model.Batch;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.sauce.SauceWithIncome;
import com.pansauce.model.sauce.SauceWithRecipe;
import com.pansauce.model.sauce.SauceWithSalesCount;

import java.util.List;

public interface SauceDao extends GenericDao<Sauce, String> {

    List<Sauce> getSaucesWithNameStartingWith(String prefix);
    List<Sauce> getSaucesWithNumberStartingWith(String prefix);

    List<Sauce> getAllSaucesSortedByName();
    List<Sauce> getAllSaucesSortedByType();
    List<Sauce> getAllSaucesSortedByNumber();

    List<Batch> getAllBatchesOfSauceSortedByStatus(String sauceKey);
    List<Batch> getAllBatchesOfSauceSortedByPrice(String sauceKey);
    List<Batch> getAllBatchesOfSauceSortedByProdDate(String sauceKey);

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
}