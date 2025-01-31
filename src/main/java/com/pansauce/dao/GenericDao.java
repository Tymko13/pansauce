package com.pansauce.dao;

import java.util.List;

public interface GenericDao<T> {

    List<T> findAll();
    void add(T t);

}
