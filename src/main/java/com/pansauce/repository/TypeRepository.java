package com.pansauce.repository;

import com.pansauce.dao.TypeDao;
import com.pansauce.model.Type;
import com.pansauce.util.RandomKeyGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.TYPE_KEY_LENGTH;
import static com.pansauce.constants.query.TypeQuery.*;
import static com.pansauce.constants.rowMapper.ModelRowMapper.TYPE_ROW_MAPPER;

@Repository(value = "typeRepo")
public class TypeRepository implements TypeDao {

    private final JdbcTemplate jdbc;

    public TypeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Type model, String typeKey) {
        jdbc.update(ADD_TYPE,
                typeKey,
                model.getTypeName());
    }

    @Override
    public void add(Type type) {
        RandomKeyGenerator keyGenerator = new RandomKeyGenerator(TYPE_KEY_LENGTH);
        String batchKey = keyGenerator.nextString();
        insert(type, batchKey);
    }

    @Override
    public List<Type> findAll() {
        return jdbc.query(GET_ALL_TYPES, TYPE_ROW_MAPPER);
    }

    @Override
    public Type findByKey(String key) {
        List<Type> result = jdbc.query(GET_TYPE_BY_KEY, TYPE_ROW_MAPPER, key);
        return result.isEmpty() ? null : result.getFirst();
    }

    @Override
    public void delete(String key) {
        jdbc.update(DELETE_TYPE_BY_KEY, key);
    }

    @Override
    public boolean exists(String key) {
        return findByKey(key) != null;
    }
}