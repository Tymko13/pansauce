package com.pansauce.model.sauce;

import com.pansauce.model.SauceIngredient;

import java.util.List;

public class SauceWithRecipe extends Sauce {

    private List<SauceIngredient> recipe;

    public List<SauceIngredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<SauceIngredient> recipe) {
        this.recipe = recipe;
    }
}