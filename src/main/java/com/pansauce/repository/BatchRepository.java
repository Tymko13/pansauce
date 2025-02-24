package com.pansauce.repository;

import com.pansauce.dao.BatchDao;
import com.pansauce.model.Batch;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.BATCH_KEY_LENGTH;
import static com.pansauce.constants.query.BatchQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.BATCH_ROW_MAPPER;

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
}
