package com.pansauce.controller;

import com.pansauce.dao.BatchDao;
import com.pansauce.model.Batch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BatchController {

    private final BatchDao batchRepository;

    public BatchController(
            @Qualifier(value = "batchRepo") BatchDao batchRepository
    ) {
        this.batchRepository = batchRepository;
    }

    @GetMapping("/batch")
    public List<Batch> getBatches() {
        return batchRepository.findAll();
    }

    @GetMapping("/batch/{key}")
    public Batch getBatch(
            @PathVariable String key
    ) {
        return batchRepository.findByKey(key);
    }

    @PostMapping("/batch")
    public void addBatch(
            @RequestBody Batch batch
    ) {
        batchRepository.add(batch);
    }

}