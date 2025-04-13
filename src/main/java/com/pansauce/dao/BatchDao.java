package com.pansauce.dao;

import com.pansauce.model.Batch;
import com.pansauce.model.analysis.TotalAmount;
import com.pansauce.model.analysis.TotalIncome;
import com.pansauce.model.dto.BatchDTO;

import java.util.Date;
import java.util.List;

public interface BatchDao extends GenericDao<Batch, String> {

    List<Batch> getBatchesByNumber(String number);

    List<Batch> getAllBatchesSortedByNumber();
    List<Batch> getAllBatchesSortedBySauceQuantity();
    List<Batch> getAllBatchesSortedByProductionDate();
    List<Batch> getAllBatchesSortedByStatus();
    List<Batch> getAllBatchesSortedByPrice();

    List<Batch> getAllBatchesWithStatusInStockSortedByProdDate();
    List<Batch> getAllBatchesWithStatusSoldSortedByProdDate();

    TotalIncome getIncomeFromSoldBatchesBetweenDates(Date from, Date to);
    TotalAmount getAmountOfSoldBatchesBetweenDates(Date from, Date to);

    TotalAmount getAmountOfSoldBatchesBetweenDatesBySauceKey(Date from, Date to, String typeKey);
    TotalIncome getIncomeFromSoldBatchesBetweenDatesBySauceKey(Date from, Date to, String typeKey);

    TotalAmount getAmountOfSoldBatchesBetweenDatesByTypeKey(Date from, Date to, String typeKey);
    TotalIncome getIncomeFromSoldBatchesBetweenDatesByTypeKey(Date from, Date to, String typeKey);

    void updateBatch(BatchDTO batchDTO);
}