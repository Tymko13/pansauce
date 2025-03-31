package com.pansauce.controller;

import com.pansauce.model.Sauce;
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
    public List<Sauce> findAllSauce() {
        return sauceService.getAllSauce();
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