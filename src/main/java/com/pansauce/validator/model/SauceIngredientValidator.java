package com.pansauce.validator.model;

import com.pansauce.model.SauceIngredient;
import com.pansauce.validator.attribute.NameAttributeValidator;
import com.pansauce.validator.attribute.WeightAttributeValidator;

import java.util.List;

public class SauceIngredientValidator implements ModelValidator<SauceIngredient> {

    private ChainValidatorHandler validatorHandler;

    @Override
    public List<String> validate(SauceIngredient ingredient) {
        buildChain(ingredient);
        return validatorHandler.validate();
    }

    private void buildChain(SauceIngredient ingredient) {
        NameAttributeValidator nameValidator =
                new NameAttributeValidator(ingredient.getName());
        nameValidator.setRequired(true);
        WeightAttributeValidator weightValidator =
                new WeightAttributeValidator(Double.toString(ingredient.getWeight()));
        weightValidator.setRequired(true);
        weightValidator.setMax(10000.0);
        validatorHandler.chain(nameValidator);
    }

}