package com.pansauce.model;

import java.math.BigDecimal;

public class Sauce {
    private String number;
    private String name;
    private String type;
    private int shelfLife;
    private double weight;
    private BigDecimal cost;

    public Sauce() {}

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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
}
