package com.pansauce.controller;

import com.pansauce.dao.IngredientDao;
import com.pansauce.model.Ingredient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/ingredient/{key}")
    public Ingredient getIngredientByKey(
            @PathVariable String key
    ) {
        return ingredientRepository.findByKey(key);
    }

    @PostMapping("/ingredient")
    public void addIngredient(
            @RequestBody Ingredient ingredient
    ) {
        ingredientRepository.add(ingredient);
    }

    @DeleteMapping("/ingredient/{key}")
    public void deleteIngredient(
            @PathVariable String key
    ) {
        ingredientRepository.delete(key);
    }
}
