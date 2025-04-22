package com.pansauce.dao;

import com.pansauce.model.basic.Ingredient;

import java.util.List;

public interface IngredientDao extends GenericDao<Ingredient, String> {

    List<Ingredient> getAllIngredientsSortedByNumber();
    List<Ingredient> getAllIngredientsSortedByName();

    List<Ingredient> getIngredientsWithNameStartingWithSortedByNumber(String prefix);
    List<Ingredient> getIngredientsWithNameStartingWithSortedByName(String prefix);

    List<Ingredient> getIngredientWithNumberStartingWithSortedByNumber(String prefix);
    List<Ingredient> getIngredientWithNumberStartingWithSortedByName(String prefix);

}