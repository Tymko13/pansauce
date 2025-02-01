package com.pansauce.dao;

import java.util.List;

// T - model
// U - primary key

public interface GenericDao<T, U> {
    void insert(T t, U u);
    void add(T t);
    List<T> findAll();
    T findByKey(U key);
    void delete(U key);
    void update(U key, T t);
}