package com.pansauce.controller;

import com.pansauce.model.Batch;
import com.pansauce.service.BatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BatchController {

    private final BatchService batchService;

    public BatchController(
            BatchService batchService
    ) {
        this.batchService = batchService;
    }

    @GetMapping("/batch")
    public List<Batch> getBatches() {
        return batchService.getAllBatches();
    }

    @GetMapping("/batch/{key}")
    public Batch getBatchByKey(
            @PathVariable String key
    ) {
        return batchService.getBatchByKey(key);
    }

    @PostMapping("/batch")
    public void addBatch(
            @RequestBody Batch batch
    ) {
        batchService.addBatch(batch);
    }

    @DeleteMapping("/batch/{key}")
    public void deleteBatch(
            @PathVariable String key
    ) {
        batchService.deleteBatch(key);
    }

}