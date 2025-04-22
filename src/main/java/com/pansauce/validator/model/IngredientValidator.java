package com.pansauce.validator.model;

import com.pansauce.model.basic.Ingredient;
import com.pansauce.validator.attribute.ProductNameAttributeValidator;

import java.util.List;

public class IngredientValidator implements ModelValidator<Ingredient> {

    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

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
