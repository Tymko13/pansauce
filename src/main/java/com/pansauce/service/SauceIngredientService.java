package com.pansauce.service;

import com.pansauce.dao.SauceDao;
import com.pansauce.dao.SauceIngredientDao;
import com.pansauce.exception.sauce.NonExistingSauceException;
import com.pansauce.exception.sauceIngredient.SauceWithoutRecipeException;
import com.pansauce.model.SauceIngredient;
import com.pansauce.validator.model.SauceIngredientValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SauceIngredientService {

    private final SauceDao sauceRepository;
    private final SauceIngredientDao ingredientRepository;

    public SauceIngredientService(
            @Qualifier(value = "sauceRepo")
            SauceDao sauceRepository,
            @Qualifier(value = "sauceIngredientRepo")
            SauceIngredientDao ingredientRepository
    ) {
        this.sauceRepository = sauceRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<SauceIngredient> getSauceIngredientsByKey(String key) {
        validateSauceExistence(key);
        List<SauceIngredient> ingredients = ingredientRepository.findAllSauceIngredientsByKey(key);
        if (ingredients.isEmpty())
            throw new SauceWithoutRecipeException();
        return ingredients;
    }

    public void addSauceIngredient(String key, SauceIngredient ingredient) {
        validateSauceExistence(key);
        SauceIngredientValidator validator = new SauceIngredientValidator();
        List<String> errorMessages = validator.validate(ingredient);
        if (errorMessages.isEmpty())
            throw new SauceWithoutRecipeException();
        ingredientRepository.addSauceIngredientByKey(key, ingredient);
    }

    private void validateSauceExistence(String key) {
        if (!sauceRepository.exists(key))
            throw new NonExistingSauceException();
    }

}
