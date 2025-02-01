package com.pansauce.controller;

import com.pansauce.dao.SauceDao;
import com.pansauce.model.Sauce;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/sauce")
    public void addSauce(
            @RequestBody Sauce sauce
    ) {
        repository.add(sauce);
    }

}
