package com.pansauce.model.order;

import java.util.List;

public class OrderWithBatchKeys extends Order {

    private List<String> batchKeys;

    public List<String> getBatchKeys() {
        return batchKeys;
    }

    public void setBatchKeys(List<String> batchKeys) {
        this.batchKeys = batchKeys;
    }
}