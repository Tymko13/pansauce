package com.pansauce.validator.model;

import com.pansauce.model.Ingredient;
import com.pansauce.validator.attribute.NameAttributeValidator;

import java.util.List;

public class IngredientValidator implements ModelValidator<Ingredient> {

    private ChainValidatorHandler validatorHandler;

    @Override
    public List<String> validate(Ingredient ingredient) {
        buildChain(ingredient);
        return validatorHandler.validate();
    }

    private void buildChain(Ingredient ingredient) {
        NameAttributeValidator nameValidator =
                new NameAttributeValidator(ingredient.getName());
        nameValidator.setRequired(true);
        validatorHandler.chain(nameValidator);
    }

}
