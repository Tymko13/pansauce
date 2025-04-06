package com.pansauce.controller;

import com.pansauce.model.Batch;
import com.pansauce.model.analysis.TotalAmount;
import com.pansauce.model.analysis.TotalIncome;
import com.pansauce.service.BatchService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public List<Batch> getAllBatchesSortedBy(
            @RequestParam(value = "sorted", defaultValue = "prod_date")
            String attribute
    ) {
        return batchService.getAllBatchesSortedBy(attribute);
    }

    @GetMapping(value = "/batch", params = {"status"})
    public List<Batch> getAllBatchesWithStatus(
            @RequestParam(value = "status")
            String status
    ) {
        return batchService.getAllBatchesWithStatus(status);
    }

    @GetMapping(value = "/batch/amount", params = {"from", "to"})
    public TotalAmount getAmountOfSoldBatchesBetweenDates(
            @RequestParam("from") Date from,
            @RequestParam("to") Date to
    ) {
        return batchService.getAmountOfSoldBatchesBetweenDates(from, to);
    }

    @GetMapping(value = "/batch/income", params = {"from", "to"})
    public TotalIncome getIncomeFromSoldBatchesBetweenDates(
            @RequestParam("from") Date from,
            @RequestParam("to") Date to
    ) {
        return batchService.getIncomeFromSoldBatchesBetweenDates(from, to);
    }

    @GetMapping(value = "/batch/amount", params = {"sauce", "from", "to"})
    public TotalAmount getAmountOfSoldBatchesBetweenDatesBySauceKey(
            @RequestParam("sauce") String sauceKey,
            @RequestParam("from") Date from,
            @RequestParam("to") Date to
    ) {
        return batchService.getAmountOfSoldBatchesBetweenDatesBySauceKey(from, to, sauceKey);
    }

    @GetMapping(value = "/batch/income", params = {"sauce", "from", "to"})
    public TotalIncome getIncomeFromSoldBatchesBetweenDatesBySauceKey(
            @RequestParam("sauce") String sauceKey,
            @RequestParam("from") Date from,
            @RequestParam("to") Date to
    ) {
        return batchService.getIncomeFromSoldBatchesBetweenDatesBySauceKey(from, to, sauceKey);
    }

    @GetMapping(value = "/batch/amount", params = {"type", "from", "to"})
    public TotalAmount getAmountOfSoldBatchesBetweenDatesByTypeKey(
            @RequestParam("type") String typeKey,
            @RequestParam("from") Date from,
            @RequestParam("to") Date to
    ) {
        return batchService.getAmountOfSoldBatchesBetweenDatesByTypeKey(from, to, typeKey);
    }

    @GetMapping(value = "/batch/income", params = {"type", "from", "to"})
    public TotalIncome getIncomeFromSoldBatchesBetweenDatesByTypeKey(
            @RequestParam("type") String typeKey,
            @RequestParam("from") Date from,
            @RequestParam("to") Date to
    ) {
        return batchService.getIncomeFromSoldBatchesBetweenDatesByTypeKey(from, to, typeKey);
    }

    @GetMapping("/batch/{key}")
    public Batch getBatchByKey(
            @PathVariable String key
    ) {
        return batchService.getBatchByKey(key);
    }

    @GetMapping(value = "/batch", params = {"number"})
    public List<Batch> getBatchesByNumber(
            @RequestParam("number") String number
    ) {
        return batchService.getBatchesByNumber(number);
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