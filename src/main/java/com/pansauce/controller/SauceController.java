package com.pansauce.controller;

import com.pansauce.model.basic.Batch;
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

    @GetMapping(value = "/sauce", params = {"sorted"})
    public List<Sauce> findAllSauce(
            @RequestParam("sorted") String attribute
    ) {
        return sauceService.getAllSauce(attribute);
    }

    @GetMapping(value = "/sauce/batch", params = {"number", "sorted"})
    public List<Batch> getSauceBatches(
            @RequestParam("number") String sauceNumber,
            @RequestParam("sorted") String attribute
    ) {
        return sauceService.getSauceBatchesBySauceKey(attribute, sauceNumber);
    }

    @GetMapping(value = "/sauce/batch", params = {"name", "sorted"})
    public List<Batch> getSauceBatchesBySauceName(
            @RequestParam("name") String sauceName,
            @RequestParam("sorted") String attribute
    ) {
        return sauceService.getSauceBatchesBySauceName(attribute, sauceName);
    }

    @GetMapping("sauce/recipe")
    public List<SauceWithRecipe> getAllSauceWithRecipe(
            @RequestParam("sorted") String attribute
    ) {
        return sauceService.getAllSauceWithRecipe(attribute);
    }

    @GetMapping("/sauce/income")
    public List<SauceWithIncome> getFiveSaucesWithIncome(
            @RequestParam("popularity") String popularity
    ) {
        return sauceService.getFiveSauceWithIncome(popularity);
    }

    @GetMapping("/sauce/sales")
    public List<SauceWithSalesCount> getFiveSaucesWithSalesCount(
            @RequestParam("popularity") String popularity
    ) {
        return sauceService.getFiveSauceWithSalesCount(popularity);
    }

    @GetMapping("/sauce/recipe/income")
    public List<SauceWithRecipe> getFiveSaucesRecipeWithIncome(
            @RequestParam("popularity") String popularity
    ) {
        return sauceService.getFiveSauceRecipeWithIncome(popularity);
    }

    @GetMapping("/sauce/recipe/sales")
    public List<SauceWithRecipe> getFiveSaucesRecipeWithSalesCount(
            @RequestParam("popularity") String popularity
    ) {
        return sauceService.getFiveSauceRecipeWithSalesCount(popularity);
    }

    @GetMapping("/sauce/{key}")
    public Sauce getSauceByKey(
            @PathVariable String key
    ) {
        return sauceService.getSauceByKey(key);
    }

    @GetMapping(value = "/sauce", params = {"number", "sorted"})
    public List<Sauce> getSauceByNumberPrefixSortedBy(
        @RequestParam("number") String number,
        @RequestParam("sorted") String attribute
    ) {
        return sauceService.getSauceWithNumberPrefixSortedBy(number, attribute);
    }

    @GetMapping(value = "/sauce", params = {"name", "sorted"})
    public List<Sauce> getSauceByNamePrefixSortedBy(
            @RequestParam("name") String name,
            @RequestParam("sorted") String attribute
    ) {
        return sauceService.getSauceWithNamePrefixSortedBy(name, attribute);
    }

    @GetMapping(value = "/sauce/without", params = {"ing", "type"})
    public List<Sauce> getSaucesWithoutTypeWithNumberAndWithoutIngredientWithNumber(
            @RequestParam("ing") String ingredientNumber,
            @RequestParam("type") String typeNumber
    ) {
        return sauceService.getSaucesWithoutTypeNumberAndWithoutIngredientNumber(ingredientNumber, typeNumber);
    }

    @GetMapping(value = "/sauce/alike_recipe/{key}")
    public List<Sauce> getSauceThatContainsRecipeOfSauceWithKey(
            @PathVariable("key") String sauceNumber
    ) {
        return sauceService.getSauceThatContainsRecipeOfSauceWithKey(sauceNumber);
    }

    @PostMapping("/sauce")
    public void addSauce(
            @RequestBody SauceWithRecipe sauce
    ) {
        sauceService.addSauceWithRecipe(sauce);
    }

    @DeleteMapping("/sauce/{key}")
    public void deleteSauce(
            @PathVariable String key
    ) {
        sauceService.deleteSauce(key);
    }

    @PatchMapping("/sauce")
    public void updateSauce(
            @RequestBody SauceWithRecipe sauce
    ) {
        sauceService.updateSauce(sauce);
    }

}