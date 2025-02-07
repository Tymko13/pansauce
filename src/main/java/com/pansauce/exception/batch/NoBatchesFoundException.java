package com.pansauce.exception.batch;

import com.pansauce.constants.enums.BatchErrorMessage;

public class NoBatchesFoundException extends RuntimeException {

    public NoBatchesFoundException() {
        super(BatchErrorMessage.NO_BATCHES_FOUND.toString());
    }

}
