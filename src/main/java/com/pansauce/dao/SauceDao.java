package com.pansauce.dao;

import com.pansauce.model.Sauce;
import com.pansauce.model.SauceIngredient;

import java.util.List;

public interface SauceDao extends GenericDao<Sauce, String> {

    List<SauceIngredient> findAllSauceIngredientsByKey(String sauceKey);
    void addSauceIngredientByKey(String sauceKey, SauceIngredient sauceIngredient);

}