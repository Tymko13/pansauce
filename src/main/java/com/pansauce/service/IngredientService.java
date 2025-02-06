package com.pansauce.service;

import com.pansauce.dao.IngredientDao;
import com.pansauce.exception.ingredient.*;
import com.pansauce.model.Ingredient;
import com.pansauce.validator.model.IngredientValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientDao ingredientRepository;

    public IngredientService(
            @Qualifier(value = "ingredientRepo")
            IngredientDao ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        if (ingredients.isEmpty())
            throw new NoIngredientsFoundException();
        return ingredients;
    }

    public Ingredient getIngredientByKey(String key) {
        if (!ingredientRepository.exists(key))
            throw new NonExistingIngredientException();
        return ingredientRepository.findByKey(key);
    }

    public void addIngredient(Ingredient ingredient) {
        IngredientValidator validator = new IngredientValidator();
        List<String> errorMessages = validator.validate(ingredient);
        if (errorMessages.isEmpty())
            ingredientRepository.add(ingredient);
        else throw new InvalidIngredientException(errorMessages);
    }

    public void deleteIngredient(String key) {
        if (!ingredientRepository.exists(key))
            throw new NonExistingIngredientException();
        ingredientRepository.delete(key);
    }

}