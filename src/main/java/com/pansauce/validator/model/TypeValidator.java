package com.pansauce.validator.model;

import com.pansauce.model.Type;
import com.pansauce.validator.attribute.TypeNameAttributeValidator;

import java.util.List;

public class TypeValidator implements ModelValidator<Type> {

    private final ChainValidatorHandler validatorHandler
            = new ChainValidatorHandler();

    @Override
    public List<String> validate(Type model) {
        buildChain(model);
        return validatorHandler.validate();
    }

    private void buildChain(Type type) {
        chainTypeNameValidator(type);
    }

    private void chainTypeNameValidator(Type type){
        TypeNameAttributeValidator typeValidator =
                new TypeNameAttributeValidator(type.getTypeName());
        typeValidator.setRequired(true);
        validatorHandler.chain(typeValidator);
    }

}