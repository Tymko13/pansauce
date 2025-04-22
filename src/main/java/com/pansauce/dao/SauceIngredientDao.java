package com.pansauce.dao;

import com.pansauce.model.basic.SauceIngredient;

import java.util.List;

public interface SauceIngredientDao {

    List<SauceIngredient> findAllSauceIngredientsByKey(String sauceKey);
    void addSauceIngredientByKey(String sauceKey, SauceIngredient sauceIngredient);

}
