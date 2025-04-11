package com.pansauce.dao;

import java.util.List;

public interface GenericDao<T, U> {
    void insert(T model, U key);
    void add(T model);
    List<T> findAll();
    T findByKey(U key);
    void delete(U key);
    boolean exists(U key);
}