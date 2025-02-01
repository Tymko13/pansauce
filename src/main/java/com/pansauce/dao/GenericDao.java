package com.pansauce.dao;

import java.util.List;

// T - model
// U - id

public interface GenericDao<T, U> {
    void insert(T t, U id);
    void add(T t);
    List<T> findAll();
    T findByKey(U id);
    void delete(U id);
}