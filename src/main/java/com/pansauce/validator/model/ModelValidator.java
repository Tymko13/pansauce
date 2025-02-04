package com.pansauce.validator.model;

import java.util.List;

public interface ModelValidator<M> {

    List<String> validate(M model);

}
