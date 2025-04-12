package com.pansauce.validator.model;

import com.pansauce.model.order.Order;
import com.pansauce.validator.attribute.CostAttributeValidator;

import java.util.List;

public class OrderValidator implements ModelValidator<Order> {
    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

    @Override
    public List<String> validate(Order order) {
        buildChain(order);
        return validatorHandler.validate();
    }

    private void buildChain(Order order) {
        chainDeliveryCostValidator(order);
    }

    private void chainDeliveryCostValidator(Order order) {
        CostAttributeValidator costValidator =
                new CostAttributeValidator(String.valueOf(order.getDeliveryCost()));
        costValidator.setRequired(false);
        validatorHandler.chain(costValidator);
    }

}
