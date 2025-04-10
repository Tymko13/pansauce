package com.pansauce.service;

import com.pansauce.dao.SauceDao;
import com.pansauce.dao.TypeDao;
import com.pansauce.exception.batch.NonExistingBatchException;
import com.pansauce.exception.sauce.*;
import com.pansauce.model.Batch;
import com.pansauce.model.dto.SauceDTO;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.Type;
import com.pansauce.model.sauce.SauceWithIncome;
import com.pansauce.model.sauce.SauceWithRecipe;
import com.pansauce.model.sauce.SauceWithSalesCount;
import com.pansauce.validator.model.SauceValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SauceService {

    private final SauceDao sauceRepository;
    private final TypeDao typeRepository;

    public SauceService(
            @Qualifier(value = "sauceRepo")
            SauceDao sauceRepository,
            @Qualifier(value = "typeRepo")
            TypeDao typeRepository
    ) {
        this.sauceRepository = sauceRepository;
        this.typeRepository = typeRepository;
    }

    public List<Sauce> getAllSauce(String attribute) {
        return switch (attribute) {
            case "name" -> sauceRepository.getAllSaucesSortedByName();
            case "type" -> sauceRepository.getAllSaucesSortedByType();
            case "number" -> sauceRepository.getAllSaucesSortedByNumber();
            default -> new ArrayList<>();
        };
    }

    public List<Batch> getSauceBatchesBySauceKey(String attribute, String sauceKey) {
        return switch (attribute) {
            case "prod_date" -> sauceRepository.getAllBatchesOfSauceSortedByProdDateBySauceKey(sauceKey  + "%");
            case "price" -> sauceRepository.getAllBatchesOfSauceSortedByPriceBySauceKey(sauceKey  + "%");
            case "status" -> sauceRepository.getAllBatchesOfSauceSortedByStatusBySauceKey(sauceKey + "%");
            case "number" -> sauceRepository.getAllBatchesOfSauceSortedByNumberBySauceKey(sauceKey  + "%");
            case "sauce_quantity" -> sauceRepository.getAllBatchesOfSauceSortedBySauceQuantityBySauceKey(sauceKey  + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Batch> getSauceBatchesBySauceName(String attribute,String sauceName) {
        return switch (attribute) {
            case "prod_date" -> sauceRepository.getAllBatchesOfSauceSortedByProdDateBySauceName(sauceName + "%");
            case "price" -> sauceRepository.getAllBatchesOfSauceSortedByPriceBySauceName(sauceName + "%");
            case "status" -> sauceRepository.getAllBatchesOfSauceSortedByStatusBySauceName(sauceName + "%");
            case "number" -> sauceRepository.getAllBatchesOfSauceSortedByNumberBySauceName(sauceName  + "%");
            case "sauce_quantity" -> sauceRepository.getAllBatchesOfSauceSortedBySauceQuantityBySauceName(sauceName  + "%");
            default -> new ArrayList<>();
        };
    }

    public List<SauceWithRecipe> getAllSauceWithRecipe(String attribute) {
        return switch (attribute) {
            case "name" -> sauceRepository.getAllSaucesWithRecipeSortedByName();
            case "count" -> sauceRepository.getAllSaucesWithRecipeSortedByWeight();
            default -> new ArrayList<>();
        };
    }

    public List<SauceWithIncome> getFiveSauceWithIncome(String attribute) {
        return switch (attribute) {
            case "top" -> sauceRepository.getBestFiveSaucesByIncome();
            case "last" -> sauceRepository.getWorstFiveSaucesByIncome();
            default -> new ArrayList<>();
        };
    }

    public List<SauceWithSalesCount> getFiveSauceWithSalesCount(String attribute) {
        return switch (attribute) {
            case "top" -> sauceRepository.getBestFiveSaucesBySales();
            case "last" -> sauceRepository.getWorstFiveSaucesBySales();
            default -> new ArrayList<>();
        };
    }

    public List<SauceWithRecipe> getFiveSauceRecipeWithIncome(String attribute) {
        return switch (attribute) {
            case "top" -> sauceRepository.getBestFiveSaucesWithRecipeByIncome();
            case "last" -> sauceRepository.getWorstFiveSaucesWithRecipeByIncome();
            default -> new ArrayList<>();
        };
    }

    public List<SauceWithRecipe> getFiveSauceRecipeWithSalesCount(String attribute) {
        return switch (attribute) {
            case "top" -> sauceRepository.getBestFiveSaucesWithRecipeBySales();
            case "last" -> sauceRepository.getWorstFiveSaucesWithRecipeBySales();
            default -> new ArrayList<>();
        };
    }

    public void updateSauce(SauceDTO sauce) {
        String sauceNumber = sauce.getNumber();
        if (!sauceRepository.exists(sauceNumber))
            throw new NonExistingSauceException();
        sauceRepository.updateSauce(sauce);
    }

    public List<Sauce> getSauceByNumber(String number) {
        return sauceRepository.getSaucesWithNumberStartingWith(number + "%");
    }

    public List<Sauce> getSauceByName(String name) {
        return sauceRepository.getSaucesWithNameStartingWith(name + "%");
    }

    public Sauce getSauceByKey(String key) {
        if (!sauceRepository.exists(key))
            throw new NonExistingSauceException();
        return sauceRepository.findByKey(key);
    }

    public void addSauce(Sauce sauce) {
        SauceValidator validator = new SauceValidator();
        List<String> errorMessages = validator.validate(sauce);
        if (errorMessages.isEmpty())
            sauceRepository.add(sauce);
        else throw new InvalidSauceException(errorMessages);
    }

    public void deleteSauce(String key) {
        if (sauceRepository.exists(key))
            deleteSauce(key);
        else throw new NonExistingSauceException();
    }

    public Type getSauceTypeByKey(String sauceKey) {
        return typeRepository.findByKey(sauceKey);
    }

}
