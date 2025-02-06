package com.pansauce.controller;

import com.pansauce.model.Ingredient;
import com.pansauce.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(
            IngredientService ingredientService
    ) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/ingredient/{key}")
    public Ingredient getIngredientByKey(
            @PathVariable String key
    ) {
        return ingredientService.getIngredientByKey(key);
    }

    @PostMapping("/ingredient")
    public void addIngredient(
            @RequestBody Ingredient ingredient
    ) {
        ingredientService.addIngredient(ingredient);
    }

    @DeleteMapping("/ingredient/{key}")
    public void deleteIngredient(
            @PathVariable String key
    ) {
        ingredientService.deleteIngredient(key);
    }
}
