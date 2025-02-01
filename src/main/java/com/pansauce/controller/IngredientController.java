package com.pansauce.controller;

import com.pansauce.dao.IngredientDao;
import com.pansauce.model.Ingredient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    private final IngredientDao ingredientRepository;

    public IngredientController(
            @Qualifier(value = "ingredientRepo") IngredientDao ingredientRepository
    ) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/ingredient")
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping("/ingredient")
    public void addIngredient(
            @RequestBody Ingredient ingredient
    ) {
        ingredientRepository.add(ingredient);
    }
}
