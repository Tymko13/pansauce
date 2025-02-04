package com.pansauce.controller;

import com.pansauce.dao.SauceIngredientDao;
import com.pansauce.model.SauceIngredient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SauceIngredientController {

    private final SauceIngredientDao repository;

    public SauceIngredientController(SauceIngredientDao repository) {
        this.repository = repository;
    }

    @GetMapping("/sauce/{key}/recipe")
    public List<SauceIngredient> getSauceIngredientsByKey(
            @PathVariable String key
    ) {
        return repository.findAllSauceIngredientsByKey(key);
    }

    @PostMapping("/sauce/{key}/recipe")
    public void addToSauceIngredient(
            @PathVariable String key,
            @RequestBody SauceIngredient ingredient
    ) {
        repository.addSauceIngredientByKey(key, ingredient);
    }

}
