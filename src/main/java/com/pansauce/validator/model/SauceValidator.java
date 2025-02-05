package com.pansauce.validator.model;

import com.pansauce.model.Sauce;
import com.pansauce.validator.attribute.*;

import java.util.List;

public class SauceValidator implements ModelValidator<Sauce> {

    private ChainValidatorHandler validatorHandler;

    @Override
    public List<String> validate(Sauce sauce) {
        buildChain(sauce);
        return validatorHandler.validate();
    }

    private void buildChain(Sauce sauce) {
        chainProductNameValidator(sauce);
        chainTypeValidator(sauce);
        chainShelfLifeValidator(sauce);
        chainWeightValidator(sauce);
        chainSauceValidator(sauce);
    }

    private void chainProductNameValidator(Sauce sauce){
        ProductNameAttributeValidator nameValidator =
                new ProductNameAttributeValidator(sauce.getName());
        nameValidator.setRequired(true);
        validatorHandler.chain(nameValidator);
    }

    private void chainTypeValidator(Sauce sauce){
        TypeNameAttributeValidator typeValidator =
                new TypeNameAttributeValidator(sauce.getType());
        typeValidator.setRequired(true);
        validatorHandler.chain(typeValidator);
    }

    private void chainShelfLifeValidator(Sauce sauce){
        ShelfLifeAttributeValidator shelfLifeValidator =
                new ShelfLifeAttributeValidator(String.valueOf(sauce.getShelfLife()));
        shelfLifeValidator.setRequired(true);
        validatorHandler.chain(shelfLifeValidator);
    }

    private void chainWeightValidator(Sauce sauce){
        WeightAttributeValidator weightValidator =
                new WeightAttributeValidator(String.valueOf(sauce.getWeight()));
        weightValidator.setRequired(true);
        validatorHandler.chain(weightValidator);
    }

    private void chainSauceValidator(Sauce sauce){
        CostAttributeValidator costValidator =
                new CostAttributeValidator(String.valueOf(sauce.getCost()));
        costValidator.setRequired(true);
        validatorHandler.chain(costValidator);
    }

}
