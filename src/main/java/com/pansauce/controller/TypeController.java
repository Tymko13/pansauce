package com.pansauce.controller;

import com.pansauce.model.basic.Type;
import com.pansauce.model.sauce.Sauce;
import com.pansauce.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(value = "/type", params = {"number", "sorted"})
    public List<Sauce> getSaucesWithTypeNumberSortedBy(
            @RequestParam("number") String typeNumber,
            @RequestParam("sorted") String attribute
    ) {
        return typeService.getSaucesWithTypeNumberSortedBy(typeNumber, attribute);
    }

    @GetMapping(value = "/type", params = {"name", "sorted"})
    public List<Sauce> getSaucesWithTypeNameSortedBy(
            @RequestParam("name") String typeName,
            @RequestParam("sorted") String attribute
    ) {
        return typeService.getSaucesWithTypeNameSortedBy(typeName, attribute);
    }

    @GetMapping("/type")
    public List<Type> findAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping(value = "/type", params = {"sorted"})
    public List<Type> findAllTypesSortedBy(
            @RequestParam("sorted") String attribute
    ) {
        return typeService.getTypesSortedBy(attribute);
    }

    @GetMapping(value = "/type/search", params = {"number", "sorted"})
    public List<Type> getTypesWithNumberPrefixSortedBy(
            @RequestParam("number") String prefix,
            @RequestParam("sorted") String attribute
    ) {
        return typeService.getTypesWithNumberPrefixSortedBy(prefix, attribute);
    }

    @GetMapping(value = "/type/search", params = {"name", "sorted"})
    public List<Type> getTypesWithNamePrefixSortedBy(
            @RequestParam("name") String prefix,
            @RequestParam("sorted") String attribute
    ) {
        return typeService.getTypesWithNamePrefixSortedBy(prefix, attribute);
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

    @PatchMapping("/type")
    public void updateType(
            @RequestBody Type type
    ) {
        typeService.updateType(type);
    }

}