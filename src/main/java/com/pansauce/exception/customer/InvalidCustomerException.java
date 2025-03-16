package com.pansauce.exception.customer;

import java.util.List;

public class InvalidCustomerException extends RuntimeException {

  public InvalidCustomerException(List<String> errorMessages) {
    super(String.join("\n", errorMessages));
  }

}