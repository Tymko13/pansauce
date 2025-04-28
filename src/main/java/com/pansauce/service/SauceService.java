package com.pansauce.service;

import com.pansauce.dao.IngredientDao;
import com.pansauce.dao.SauceDao;
import com.pansauce.dao.SauceIngredientDao;
import com.pansauce.dao.TypeDao;
import com.pansauce.exception.sauce.*;
import com.pansauce.model.basic.Batch;
import com.pansauce.model.basic.SauceIngredient;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.model.basic.Type;
import com.pansauce.model.sauce.SauceWithIncome;
import com.pansauce.model.sauce.SauceWithRecipe;
import com.pansauce.model.sauce.SauceWithSalesCount;
import com.pansauce.util.RandomKeyGenerator;
import com.pansauce.validator.model.SauceValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.pansauce.constants.keyLength.KeyLength.*;

@Service
public class SauceService {

    private final SauceDao sauceRepository;
    private final TypeDao typeRepository;
    private final SauceIngredientDao sauceIngredientRepository;
    private final IngredientDao ingredientRepository;

    public SauceService(
            @Qualifier(value = "sauceRepo")
            SauceDao sauceRepository,
            @Qualifier(value = "typeRepo")
            TypeDao typeRepository,
            @Qualifier("sauceIngredientRepo")
            SauceIngredientDao sauceIngredientRepository,
            @Qualifier("ingredientRepo")
            IngredientDao ingredientRepository
    ) {
        this.sauceRepository = sauceRepository;
        this.typeRepository = typeRepository;
        this.sauceIngredientRepository = sauceIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Sauce> getAllSauce(String attribute) {
        return switch (attribute) {
            case "name" -> sauceRepository.getAllSaucesSortedByName();
            case "type" -> sauceRepository.getAllSaucesSortedByTypeName();
            case "number" -> sauceRepository.getAllSaucesSortedByNumber();
            case "price" -> sauceRepository.getAllSaucesSortedByPrice();
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

    public List<Sauce> getSauceWithNamePrefixSortedBy(String sauceName, String attribute) {
        return switch (attribute) {
            case "name" -> sauceRepository.getSaucesWithNameStartingWithSortedByName(sauceName + "%");
            case "number" -> sauceRepository.getSaucesWithNameStartingWithSortedByNumber(sauceName + "%");
            case "type" -> sauceRepository.getSaucesWithNameStartingWithSortedByTypeName(sauceName + "%");
            case "price" -> sauceRepository.getSaucesWithNameStartingWithSortedByPrice(sauceName + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Sauce> getSauceWithNumberPrefixSortedBy(String sauceNumber, String attribute) {
        return switch (attribute) {
            case "name" -> sauceRepository.getSaucesWithNumberStartingWithSortedByName(sauceNumber + "%");
            case "number" -> sauceRepository.getSaucesWithNumberStartingWithSortedByNumber(sauceNumber + "%");
            case "type" -> sauceRepository.getSaucesWithNumberStartingWithSortedByTypeName(sauceNumber + "%");
            case "price" -> sauceRepository.getSaucesWithNumberStartingWithSortedByPrice(sauceNumber + "%");
            default -> new ArrayList<>();
        };
    }

    public List<Sauce> getSaucesWithoutTypeNumberAndWithoutIngredientNumber(String ingredientNumber, String typeNumber) {
        return sauceRepository.getSaucesWithoutIngredientAndWithoutType(ingredientNumber, typeNumber);
    }

    public void updateSauce(SauceWithRecipe sauce) {
        String sauceNumber = sauce.getNumber();
        if (!sauceRepository.exists(sauceNumber))
            throw new NonExistingSauceException();
        List<SauceIngredient> ingredients = sauce.getRecipe();
        if (ingredients.isEmpty())
            throw new RuntimeException("Ingredients list is empty");
        String typeNumber = sauce.getTypeNumber();
        if (!typeRepository.exists(typeNumber)) {
            Type type = new Type();
            type.setTypeName(sauce.getTypeName());
            RandomKeyGenerator keyGenerator = new RandomKeyGenerator(TYPE_KEY_LENGTH);
            String typeKey = keyGenerator.nextString();
            typeRepository.insert(type, typeKey);
            sauce.setTypeNumber(typeKey);
        }
        sauceRepository.deleteSauceIngredients(sauceNumber);
        sauceRepository.updateSauce(sauce);
        for (SauceIngredient ingredient : ingredients) {
            String gtiNumber = ingredient.getGti();
            if (!ingredientRepository.exists(gtiNumber)) {
                RandomKeyGenerator keyGenerator = new RandomKeyGenerator(INGREDIENT_KEY_LENGTH);
                gtiNumber = keyGenerator.nextString();
                ingredientRepository.insert(ingredient, gtiNumber);
            }
            ingredient.setGti(gtiNumber);
            sauceIngredientRepository.addSauceIngredientByKey(sauceNumber, ingredient);
        }
    }

    public Sauce getSauceByKey(String key) {
        if (!sauceRepository.exists(key))
            throw new NonExistingSauceException();
        return sauceRepository.findByKey(key);
    }

    public void addSauceWithRecipe(SauceWithRecipe sauce) {
        SauceValidator validator = new SauceValidator();
        List<String> errorMessages = validator.validate(sauce);
        if (errorMessages.isEmpty())
            throw new InvalidSauceException(errorMessages);
        String typeNumber = sauce.getTypeNumber();
        if (!typeRepository.exists(typeNumber)) {
            Type type = new Type();
            type.setTypeName(sauce.getTypeName());
            RandomKeyGenerator keyGenerator = new RandomKeyGenerator(TYPE_KEY_LENGTH);
            String typeKey = keyGenerator.nextString();
            typeRepository.insert(type, typeKey);
            sauce.setTypeNumber(typeKey);
        }
        RandomKeyGenerator sauceKeyGenerator = new RandomKeyGenerator(SAUCE_KEY_LENGTH);
        String sauceNumber = sauceKeyGenerator.nextString();
        sauceRepository.insert(sauce, sauceNumber);
        for (SauceIngredient ingredient : sauce.getRecipe()) {
            String ingredientNumber = ingredient.getGti();
            if (!ingredientRepository.exists(ingredientNumber)) {
                RandomKeyGenerator keyGenerator = new RandomKeyGenerator(INGREDIENT_KEY_LENGTH);
                String gtiNumber = keyGenerator.nextString();
                ingredientRepository.insert(ingredient, gtiNumber);
                ingredient.setGti(gtiNumber);
            }
            sauceIngredientRepository.addSauceIngredientByKey(sauceNumber, ingredient);
        }
    }

    public void deleteSauce(String key) {
        if (sauceRepository.exists(key))
            deleteSauce(key);
        else throw new NonExistingSauceException();
    }

}