package com.pansauce.service;

import com.pansauce.dao.SauceDao;
import com.pansauce.dao.TypeDao;
import com.pansauce.exception.sauce.*;
import com.pansauce.model.Sauce;
import com.pansauce.model.Type;
import com.pansauce.validator.model.SauceValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SauceService {

    private final SauceDao sauceRepository;
    private final TypeDao typeRepository;

    public SauceService(
            @Qualifier(value = "sauceRepo")
            SauceDao sauceRepository,
            @Qualifier(value = "typeRepo")
            TypeDao typeRepository
    ) {
        this.sauceRepository = sauceRepository;
        this.typeRepository = typeRepository;
    }

    public List<Sauce> getAllSauce() {
        List<Sauce> sauces = sauceRepository.findAll();
        if (sauces.isEmpty())
            throw new NoSaucesFoundException();
        return sauces;
    }

    public Sauce getSauceByKey(String key) {
        if (!sauceRepository.exists(key))
            throw new NonExistingSauceException();
        return sauceRepository.findByKey(key);
    }

    public void addSauce(Sauce sauce) {
        SauceValidator validator = new SauceValidator();
        List<String> errorMessages = validator.validate(sauce);
        if (errorMessages.isEmpty())
            sauceRepository.add(sauce);
        else throw new InvalidSauceException(errorMessages);
    }

    public void deleteSauce(String key) {
        if (sauceRepository.exists(key))
            deleteSauce(key);
        else throw new NonExistingSauceException();
    }

    public Type getSauceTypeByKey(String sauceKey) {
        return typeRepository.findByKey(sauceKey);
    }

}
