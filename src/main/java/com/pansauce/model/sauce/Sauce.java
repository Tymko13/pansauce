package com.pansauce.model.sauce;

import java.math.BigDecimal;

public class Sauce {
    private String number;
    private String name;
    private int shelfLife;
    private double weight;
    private BigDecimal cost;
    private String typeNumber;
    private String typeName;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public double getWeight() {
        return weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setTypeNumber(String typeNumber) {
        this.typeNumber = typeNumber;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
