package com.pansauce.dao;

import com.pansauce.model.Type;
import com.pansauce.model.sauce.Sauce;

import java.util.List;

public interface TypeDao extends GenericDao<Type, String> {

    List<Sauce> getSaucesWithTypeNumberSortedBySauceName(String typeName);
    List<Sauce> getSaucesWithTypeNumberSortedByPrice(String typeName);

    List<Sauce> getSaucesWithTypeNameSortedBySauceName(String typeName);
    List<Sauce> getSaucesWithTypeNameSortedByPrice(String typeName);

}