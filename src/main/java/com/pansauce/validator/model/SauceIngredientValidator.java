package com.pansauce.validator.model;

import com.pansauce.model.basic.SauceIngredient;
import com.pansauce.validator.attribute.ProductNameAttributeValidator;
import com.pansauce.validator.attribute.WeightAttributeValidator;

import java.util.List;

public class SauceIngredientValidator implements ModelValidator<SauceIngredient> {

    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

    @Override
    public List<String> validate(SauceIngredient ingredient) {
        buildChain(ingredient);
        return validatorHandler.validate();
    }

    private void buildChain(SauceIngredient ingredient) {
        chainProductNameValidator(ingredient);
        chainWeightValidator(ingredient);
    }

    private void chainProductNameValidator(SauceIngredient ingredient) {
        ProductNameAttributeValidator nameValidator =
                new ProductNameAttributeValidator(ingredient.getName());
        nameValidator.setRequired(true);
        validatorHandler.chain(nameValidator);
    }

    private void chainWeightValidator(SauceIngredient ingredient) {
        WeightAttributeValidator weightValidator =
                new WeightAttributeValidator(Double.toString(ingredient.getWeight()));
        weightValidator.setRequired(true);
        weightValidator.setMax(10000.0);
        validatorHandler.chain(weightValidator);
    }

}