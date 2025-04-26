package com.pansauce.repository;

import com.pansauce.dao.BatchDao;
import com.pansauce.model.basic.Batch;
import com.pansauce.model.analysis.TotalAmount;
import com.pansauce.model.analysis.TotalIncome;
import com.pansauce.model.dto.BatchDTO;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.BATCH_KEY_LENGTH;
import static com.pansauce.constants.query.BatchQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.*;

@Repository(value = "batchRepo")
public class BatchRepository implements BatchDao {

    private final JdbcTemplate jdbc;

    public BatchRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Batch batch, String batchKey) {
        jdbc.update(ADD_BATCH,
                    batchKey,
                    batch.getQuantity(),
                    batch.getProductionDate(),
                    batch.getExpirationDate(),
                    batch.getSauceCost(),
                    batch.getCost(),
                    batch.getStatus(),
                    batch.getSauceNumber(),
                    batch.getOrderNumber());
    }

    @Override
    public void add(Batch batch) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(BATCH_KEY_LENGTH);
        String batchKey = keyGenerator.nextString();
        insert(batch, batchKey);
    }

    @Override
    public List<Batch> findAll() {
        return jdbc.query(GET_ALL_BATCH, BATCH_ROW_MAPPER);
    }

    @Override
    public Batch findByKey(String key) {
        List<Batch> result = jdbc.query(GET_BATCH_BY_KEY, BATCH_ROW_MAPPER, key);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void delete(String batchKey) {
        jdbc.update(DELETE_BATCH_BY_KEY, batchKey);
    }

    @Override
    public boolean exists(String key) {
        return findByKey(key) != null;
    }

    @Override
    public List<Batch> getBatchesByNumber(String number) {
        return jdbc.query(GET_BATCH_BY_NUMBER, BATCH_ROW_MAPPER, number);
    }

    @Override
    public List<Batch> getAllBatchesSortedByNumber() {
        return jdbc.query(GET_ALL_BATCH_SORTED_BY_NUMBER, BATCH_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesSortedBySauceQuantity() {
        return jdbc.query(GET_ALL_BATCH_SORTED_BY_SAUCE_QUANTITY, BATCH_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesSortedByProductionDate() {
        return jdbc.query(GET_ALL_BATCH_SORTED_BY_PROD_DATE, BATCH_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesSortedByStatus() {
        return jdbc.query(GET_ALL_BATCH_SORTED_BY_STATUS, BATCH_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesSortedByPrice() {
        return jdbc.query(GET_ALL_BATCH_SORTED_BY_PRICE, BATCH_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesWithStatusInStockSortedByProdDate() {
        return jdbc.query(GET_ALL_BATCH_WITH_STATUS_IN_STOCK_SORTED_BY_PROD_DATE, BATCH_ROW_MAPPER);
    }

    @Override
    public List<Batch> getAllBatchesWithStatusSoldSortedByProdDate() {
        return jdbc.query(GET_ALL_BATCH_WITH_STATUS_SOLD_SORTED_BY_PROD_DATE, BATCH_ROW_MAPPER);
    }

    @Override
    public TotalIncome getIncomeFromSoldBatchesBetweenDates(Date from, Date to) {
        List<TotalIncome> result = jdbc.query(GET_TOTAL_INCOME_FROM_SOLD_BATCHES_BETWEEN_DATES, TOTAL_INCOME_ROW_MAPPER);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public TotalAmount getAmountOfSoldBatchesBetweenDates(Date from, Date to) {
        List<TotalAmount> result = jdbc.query(GET_TOTAL_AMOUNT_OF_SOLD_BATCHES_BETWEEN_DATES, TOTAL_AMOUNT_ROW_MAPPER);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public TotalAmount getAmountOfSoldBatchesBetweenDatesBySauceKey(Date from, Date to, String sauceKey) {
        List<TotalAmount> result = jdbc.query(GET_AMOUNT_OF_SOLD_BATCHES_BETWEEN_DATES_BY_SAUCE_KEY,
                                              TOTAL_AMOUNT_ROW_MAPPER, sauceKey,
                                              from, to);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public TotalIncome getIncomeFromSoldBatchesBetweenDatesBySauceKey(Date from, Date to, String sauceKey) {
        List<TotalIncome> result = jdbc.query(GET_INCOME_FROM_SOLD_BATCHES_BETWEEN_DATES_BY_SAUCE_KEY,
                                              TOTAL_INCOME_ROW_MAPPER, sauceKey,
                                              from, to);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public TotalAmount getAmountOfSoldBatchesBetweenDatesByTypeKey(Date from, Date to, String typeKey) {
        List<TotalAmount> result = jdbc.query(GET_AMOUNT_OF_SOLD_BATCHES_BETWEEN_DATES_BY_TYPE_KEY,
                                              TOTAL_AMOUNT_ROW_MAPPER, typeKey,
                                              from, to);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public TotalIncome getIncomeFromSoldBatchesBetweenDatesByTypeKey(Date from, Date to, String typeKey) {
        List<TotalIncome> result = jdbc.query(GET_INCOME_FROM_SOLD_BATCHES_BETWEEN_DATES_BY_TYPE_KEY,
                                              TOTAL_INCOME_ROW_MAPPER, typeKey,
                                              from, to);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void updateBatch(BatchDTO batchDTO) {
        jdbc.update(UPDATE_BATCH_BY_KEY,
                batchDTO.getQuantity(),
                batchDTO.getSauceCost(),
                batchDTO.getOrderNumber(),
                batchDTO.getNumber());
    }

}