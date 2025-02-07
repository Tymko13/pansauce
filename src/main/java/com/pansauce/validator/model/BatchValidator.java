package com.pansauce.validator.model;

import com.pansauce.model.Batch;
import com.pansauce.validator.attribute.QuantityAttributeValidator;

import java.util.List;

public class BatchValidator implements ModelValidator<Batch> {

    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

    @Override
    public List<String> validate(Batch batch) {
        buildChain(batch);
        return validatorHandler.validate();
    }

    private void buildChain(Batch batch) {
        chainQuantityValidator(batch);
    }

    private void chainQuantityValidator(Batch batch) {
        QuantityAttributeValidator quantityValidator =
                new QuantityAttributeValidator(String.valueOf(batch.getQuantity()));
        quantityValidator.setRequired(true);
        validatorHandler.chain(quantityValidator);
    }

}
