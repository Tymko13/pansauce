package com.pansauce.controller;

import com.pansauce.dao.SauceDao;
import com.pansauce.model.Sauce;
import com.pansauce.model.SauceIngredient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SauceController {

    private final SauceDao repository;

    public SauceController(
            @Qualifier(value = "sauceRepo")  SauceDao repository
    ) {
        this.repository = repository;
    }

    @GetMapping("/sauce")
    public List<Sauce> findAllSauce() {
        return repository.findAll();
    }

    @GetMapping("/sauce/{key}")
    public Sauce getSauceByKey(
            @PathVariable String key
    ) {
        return repository.findByKey(key);
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

    @PostMapping("/sauce")
    public void addSauce(
            @RequestBody Sauce sauce
    ) {
        repository.add(sauce);
    }

    @DeleteMapping("/sauce/{key}")
    public void deleteSauce(
            @PathVariable String key
    ) {
        repository.delete(key);
    }

}
