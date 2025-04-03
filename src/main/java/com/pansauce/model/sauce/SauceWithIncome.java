package com.pansauce.model.sauce;

import java.math.BigDecimal;

public class SauceWithIncome {
    private String sauceNumber;
    private String sauceName;
    private BigDecimal sauceIncome;

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

    public BigDecimal getSauceIncome() {
        return sauceIncome;
    }

    public void setSauceIncome(BigDecimal sauceIncome) {
        this.sauceIncome = sauceIncome;
    }
}