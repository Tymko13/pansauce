package com.pansauce.controller;

import com.pansauce.model.Type;
import com.pansauce.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/type")
    public List<Type> findAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/type/{key}")
    public Type getTypeByKey(
            @PathVariable String key
    ) {
        return typeService.getTypeByKey(key);
    }

    @PostMapping("/type")
    public void addType(
            @RequestBody Type type
    ) {
        typeService.addType(type);
    }

    @DeleteMapping("/type/{key}")
    public void deleteType(
            @PathVariable String key
    ) {
        typeService.deleteType(key);
    }

}