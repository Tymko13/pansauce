package com.pansauce.model.sauce;

public class SauceWithSalesCount {
    private String sauceNumber;
    private String sauceName;
    private int salesCount;

    public String getSauceNumber() {
        return sauceNumber;
    }

    public void setSauceNumber(String sauceNumber) {
        this.sauceNumber = sauceNumber;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }
}