package com.pansauce.controller;

import com.pansauce.model.Batch;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.sauce.SauceWithIncome;
import com.pansauce.model.sauce.SauceWithRecipe;
import com.pansauce.model.sauce.SauceWithSalesCount;
import com.pansauce.service.SauceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SauceController {

    private final SauceService sauceService;

    public SauceController(
            SauceService sauceService
    ) {
        this.sauceService = sauceService;
    }

    @GetMapping("/sauce")
    public List<Sauce> findAllSauce(
            @RequestParam(value = "sorted")
            String attribute
    ) {
        return sauceService.getAllSauce(attribute);
    }

    @GetMapping("/sauce/{sauceKey}/batch")
    public List<Batch> getSauceBatches(
            @PathVariable String sauceKey,
            @RequestParam(value = "sorted")
            String attribute
    ) {
        return sauceService.getSauceBatches(attribute, sauceKey);
    }

    @GetMapping("sauce/recipe")
    public List<SauceWithRecipe> getAllSauceWithRecipe(
            @RequestParam(value = "sorted")
            String attribute
    ) {
        return sauceService.getAllSauceWithRecipe(attribute);
    }

    @GetMapping("/sauce/income")
    public List<SauceWithIncome> getFiveSaucesWithIncome(
            @RequestParam(value = "popularity")
            String popularity
    ) {
        return sauceService.getFiveSauceWithIncome(popularity);
    }

    @GetMapping("/sauce/sales")
    public List<SauceWithSalesCount> getFiveSaucesWithSalesCount(
            @RequestParam(value = "popularity")
            String popularity
    ) {
        return sauceService.getFiveSauceWithSalesCount(popularity);
    }

    @GetMapping("/sauce/recipe/income")
    public List<SauceWithRecipe> getFiveSaucesRecipeWithIncome(
            @RequestParam(value = "popularity")
            String popularity
    ) {
        return sauceService.getFiveSauceRecipeWithIncome(popularity);
    }

    @GetMapping("/sauce/recipe/sales")
    public List<SauceWithRecipe> getFiveSaucesRecipeWithSalesCount(
            @RequestParam(value = "popularity")
            String popularity
    ) {
        return sauceService.getFiveSauceRecipeWithSalesCount(popularity);
    }

    @GetMapping("/sauce/{key}")
    public Sauce getSauceByKey(
            @PathVariable String key
    ) {
        return sauceService.getSauceByKey(key);
    }

    @PostMapping("/sauce")
    public void addSauce(
            @RequestBody Sauce sauce
    ) {
        sauceService.addSauce(sauce);
    }

    @DeleteMapping("/sauce/{key}")
    public void deleteSauce(
            @PathVariable String key
    ) {
        sauceService.deleteSauce(key);
    }

}