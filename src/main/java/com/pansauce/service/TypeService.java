package com.pansauce.service;

import com.pansauce.dao.TypeDao;
import com.pansauce.exception.type.InvalidTypeException;
import com.pansauce.exception.type.NoTypesFoundException;
import com.pansauce.exception.type.NonExistingTypeException;
import com.pansauce.model.basic.Type;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.validator.model.TypeValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    private final TypeDao typeRepository;

    public TypeService(
            @Qualifier(value = "typeRepo")
            TypeDao typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Sauce> getSaucesWithTypeNumberSortedBy(String typeNumber, String attribute) {
        return switch (attribute) {
            case "name" -> typeRepository.getSaucesWithTypeNumberSortedBySauceName(typeNumber + "%");
            case "price" -> typeRepository.getSaucesWithTypeNumberSortedByPrice(typeNumber + "%");
            case "number" -> typeRepository.getSauceWithTypeNumberSortedBySauceNumber(typeNumber + "%");
            case "type" -> typeRepository.getSauceWithTypeNumberSortedByTypeName(typeNumber + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Sauce> getSaucesWithTypeNameSortedBy(String typeName, String attribute) {
        return switch (attribute) {
            case "name" -> typeRepository.getSaucesWithTypeNameSortedBySauceName(typeName + "%");
            case "price" -> typeRepository.getSaucesWithTypeNameSortedByPrice(typeName + "%");
            case "number" -> typeRepository.getSaucesWithTypeNameSortedBySauceNumber(typeName + "%");
            case "type" -> typeRepository.getSaucesWithTypeNameSortedByTypeName(typeName + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Type> getTypesSortedBy(String attribute) {
        return switch (attribute) {
            case "number" -> typeRepository.getTypesSortedByNumber();
            case "name" -> typeRepository.getTypesSortedByName();
            default -> new ArrayList<>();
        };
    }

    public List<Type> getTypesWithNumberPrefixSortedBy(String prefix, String attribute) {
        return switch (attribute) {
            case "number" -> typeRepository.getTypesWithNumberStartingWithSortedByNumber(prefix + '%');
            case "name" -> typeRepository.getTypesWithNumberStartingWithSortedByName(prefix + '%');
            default -> new ArrayList<>();
        };
    }

    public List<Type> getTypesWithNamePrefixSortedBy(String prefix, String attribute) {
        return switch(attribute) {
            case "number" -> typeRepository.getTypesWithNameStartingWithSortedByNumber(prefix + '%');
            case "name" -> typeRepository.getTypesWithNameStartingWithSortedByName(prefix + '%');
            default -> new ArrayList<>();
        };
    }

    public List<Type> getAllTypes() {
        List<Type> types = typeRepository.findAll();
        if (types.isEmpty())
            throw new NoTypesFoundException();
        return types;
    }

    public Type getTypeByKey(String key) {
        if (!typeRepository.exists(key))
            throw new NonExistingTypeException();
        return typeRepository.findByKey(key);
    }

    public void addType(Type type) {
        TypeValidator validator = new TypeValidator();
        List<String> errorMessages = validator.validate(type);
        if (errorMessages.isEmpty())
            typeRepository.add(type);
        else throw new InvalidTypeException(errorMessages);
    }

    public void deleteType(String key) {
        if (typeRepository.exists(key))
            typeRepository.delete(key);
        else throw new NonExistingTypeException();
    }

    public void updateType(Type type) {
        String typeNumber = type.getTypeNumber();
        getTypeByKey(typeNumber);
        typeRepository.updateType(type);
    }

}