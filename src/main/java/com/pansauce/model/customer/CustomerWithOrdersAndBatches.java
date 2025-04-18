package com.pansauce.model.customer;

import java.util.List;

public class CustomerWithOrdersAndBatches extends CustomerWithOrders {
    private List<String> batchKeys;

    public List<String> getBatchKeys() {
        return batchKeys;
    }

    public void setBatchKeys(List<String> batchKeys) {
        this.batchKeys = batchKeys;
    }
}
