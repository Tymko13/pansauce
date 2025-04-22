package com.pansauce.controller;

import com.pansauce.model.basic.SauceIngredient;
import com.pansauce.service.SauceIngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SauceIngredientController {

    private final SauceIngredientService sauceService;

    public SauceIngredientController(
            SauceIngredientService sauceService
    ) {
        this.sauceService = sauceService;
    }

    @GetMapping("/sauce/{key}/recipe")
    public List<SauceIngredient> getSauceIngredientsByKey(
            @PathVariable String key
    ) {
        return sauceService.getSauceIngredientsByKey(key);
    }

    @PostMapping("/sauce/{key}/recipe")
    public void addSauceIngredient(
            @PathVariable String key,
            @RequestBody SauceIngredient ingredient
    ) {
        sauceService.addSauceIngredient(key, ingredient);
    }

}
