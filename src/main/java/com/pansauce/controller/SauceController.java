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

    @GetMapping(value = "/sauce", params = {"number"})
    public List<Sauce> getSauceByNumber(
        @RequestParam("number") String number
    ) {
        return sauceService.getSauceByNumber(number);
    }

    @GetMapping(value = "/sauce", params = {"name"})
    public List<Sauce> getSauceByName(
            @RequestParam("name") String name
    ) {
        return sauceService.getSauceByName(name);
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