package com.pansauce.service;

import com.pansauce.dao.BatchDao;
import com.pansauce.exception.batch.*;
import com.pansauce.model.Batch;
import com.pansauce.model.analysis.TotalAmount;
import com.pansauce.model.analysis.TotalIncome;
import com.pansauce.validator.model.BatchValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BatchService {

    private final BatchDao batchRepository;

    public BatchService(
            @Qualifier(value = "batchRepo")
            BatchDao batchRepository
    ) {
        this.batchRepository = batchRepository;
    }

    public List<Batch> getAllBatchesSortedBy(String attribute) {
        return switch (attribute) {
            case "number" -> batchRepository.getAllBatchesSortedByNumber();
            case "price" -> batchRepository.getAllBatchesSortedByPrice();
            case "status" -> batchRepository.getAllBatchesSortedByStatus();
            case "sauce_quantity" -> batchRepository.getAllBatchesSortedBySauceQuantity();
            case "prod_date" -> batchRepository.getAllBatchesSortedByProductionDate();
            default -> new ArrayList<>();
        };
    }

    public List<Batch> getAllBatchesWithStatus(String status) {
        return switch (status) {
            case "IN STOCK" -> batchRepository.getAllBatchesWithStatusInStockSortedByProdDate();
            case "SOLD" -> batchRepository.getAllBatchesWithStatusSoldSortedByProdDate();
            default -> new ArrayList<>();
        };
    }

    public TotalAmount getAmountOfSoldBatchesBetweenDates(Date from, Date to) {
        return batchRepository.getAmountOfSoldBatchesBetweenDates(from, to);
    }

    public TotalIncome getIncomeFromSoldBatchesBetweenDates(Date from, Date to) {
        return batchRepository.getIncomeFromSoldBatchesBetweenDates(from, to);
    }

    public TotalAmount getAmountOfSoldBatchesBetweenDatesBySauceKey(Date from, Date to, String sauceKey) {
        return batchRepository.getAmountOfSoldBatchesBetweenDatesBySauceKey(from, to, sauceKey + "%");
    }

    public TotalIncome getIncomeFromSoldBatchesBetweenDatesBySauceKey(Date from, Date to, String sauceKey) {
        return batchRepository.getIncomeFromSoldBatchesBetweenDatesBySauceKey(from, to, sauceKey+ "%");
    }

    public TotalAmount getAmountOfSoldBatchesBetweenDatesByTypeKey(Date from, Date to, String typeKey) {
        return batchRepository.getAmountOfSoldBatchesBetweenDatesByTypeKey(from, to, typeKey+ "%");
    }

    public TotalIncome getIncomeFromSoldBatchesBetweenDatesByTypeKey(Date from, Date to, String typeKey) {
        return batchRepository.getIncomeFromSoldBatchesBetweenDatesByTypeKey(from, to, typeKey+ "%");
    }

    public List<Batch> getAllBatches() {
        List<Batch> batches = batchRepository.findAll();
        if (batches.isEmpty())
            throw new NoBatchesFoundException();
        return batches;
    }

    public Batch getBatchByKey(String key) {
        if (!batchRepository.exists(key))
            throw new NonExistingBatchException();
        return batchRepository.findByKey(key);
    }

    public void addBatch(Batch batch) {
        BatchValidator validator = new BatchValidator();
        List<String> errorMessages = validator.validate(batch);
        if (errorMessages.isEmpty())
            batchRepository.add(batch);
        else throw new InvalidBatchException(errorMessages);
    }

    public void deleteBatch(String key) {
        if (batchRepository.exists(key))
            batchRepository.delete(key);
        else throw new NonExistingBatchException();
    }

}
