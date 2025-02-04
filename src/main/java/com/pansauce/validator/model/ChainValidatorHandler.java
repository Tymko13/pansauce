package com.pansauce.validator.model;

import com.pansauce.validator.attribute.AttributeValidator;

import java.util.LinkedList;
import java.util.List;

public class ChainValidatorHandler {

    private AttributeValidator head;
    private AttributeValidator tail;

    public ChainValidatorHandler(AttributeValidator attributeValidator) {
        this.head = attributeValidator;
        this.tail = this.head;
    }

    public ChainValidatorHandler() {
        this(null);
    }

    public void chain(AttributeValidator attributeValidator) {
        if (tail == null) {
            head = attributeValidator;
            tail = head;
        } else {
            tail.setNextValidator(attributeValidator);
            tail = tail.getNextValidator();
        }
    }

    public List<String> validate() {
        AttributeValidator currValidator = head;
        List<String> errorMessages = new LinkedList<>();
        while (currValidator != null) {
            currValidator.validate(errorMessages);
            currValidator = currValidator.getNextValidator();
        }
        return errorMessages;
    }

}