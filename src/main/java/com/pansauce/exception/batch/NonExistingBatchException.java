package com.pansauce.exception.batch;

import com.pansauce.constants.enums.BatchErrorMessage;

public class NonExistingBatchException extends RuntimeException {

    public NonExistingBatchException() {
        super(BatchErrorMessage.NON_EXISTING_BATCH.toString());
    }

}
