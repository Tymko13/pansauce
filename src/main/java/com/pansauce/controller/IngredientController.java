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

    @GetMapping(value = "/ingredient", params = {"sorted"})
    public List<Ingredient> getAllIngredientSortedBy(
            @RequestParam("sorted") String attribute
    ) {
        return ingredientService.getAllIngredientsSortedBy(attribute);
    }

    @GetMapping(value = "/ingredient/search", params = {"number", "sorted"})
    public List<Ingredient> getIngredientsWithNumberStartingWithSortedBy(
            @RequestParam("number") String ingredientNumber,
            @RequestParam("sorted") String attribute
    ) {
        return ingredientService.getIngredientsWithNumberStartingWithSortedBy(ingredientNumber, attribute);
    }

    @GetMapping(value = "/ingredient/search", params = {"name", "sorted"})
    public List<Ingredient> getIngredientsWithNameStartingWithSortedBy(
            @RequestParam("name") String ingredientName,
            @RequestParam("sorted") String attribute
    ) {
        return ingredientService.getIngredientsWithNameStartingWithSortedBy(ingredientName, attribute);
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
