package com.pansauce.validator.model;

import com.pansauce.model.Ingredient;
import com.pansauce.validator.attribute.ProductNameAttributeValidator;

import java.util.List;

public class IngredientValidator implements ModelValidator<Ingredient> {

    private ChainValidatorHandler validatorHandler;

    @Override
    public List<String> validate(Ingredient ingredient) {
        buildChain(ingredient);
        return validatorHandler.validate();
    }

    private void buildChain(Ingredient ingredient) {
        chainProductNameValidator(ingredient);
    }

    private void chainProductNameValidator(Ingredient ingredient) {
        ProductNameAttributeValidator nameValidator =
                new ProductNameAttributeValidator(ingredient.getName());
        nameValidator.setRequired(true);
        validatorHandler.chain(nameValidator);
    }

}
