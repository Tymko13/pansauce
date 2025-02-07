package com.pansauce.service;

import com.pansauce.dao.BatchDao;
import com.pansauce.exception.batch.*;
import com.pansauce.model.Batch;
import com.pansauce.validator.model.BatchValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
