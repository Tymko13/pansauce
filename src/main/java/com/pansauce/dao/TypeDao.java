package com.pansauce.dao;

import com.pansauce.model.basic.Type;
import com.pansauce.model.sauce.Sauce;

import java.util.List;

public interface TypeDao extends GenericDao<Type, String> {

    List<Sauce> getSaucesWithTypeNumberSortedBySauceName(String typeName);
    List<Sauce> getSaucesWithTypeNumberSortedByPrice(String typeName);
    List<Sauce> getSauceWithTypeNumberSortedBySauceNumber(String typeNumber);
    List<Sauce> getSauceWithTypeNumberSortedByTypeName(String typeName);

    List<Sauce> getSaucesWithTypeNameSortedBySauceName(String typeName);
    List<Sauce> getSaucesWithTypeNameSortedByPrice(String typeName);
    List<Sauce> getSaucesWithTypeNameSortedBySauceNumber(String typeName);
    List<Sauce> getSaucesWithTypeNameSortedByTypeName(String typeName);

    void updateType(Type type);

}