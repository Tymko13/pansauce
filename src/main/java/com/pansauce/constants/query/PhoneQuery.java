package com.pansauce.constants.query;

public class PhoneQuery {
    public static final String GET_ALL_PHONES_BY_CUSTOMER_KEY =
            "SELECT contact_number FROM contact_number WHERE customer_number = ?";

    public static final String ADD_PHONE_TO_CUSTOMER_BY_KEY = "INSERT INTO contact_number " +
                                                        "(contact_number, customer_number) VALUES (?, ?)";
    public static final String DELETE_PHONE_FROM_CUSTOMER =
            "DELETE FROM contact_number\n" +
            "WHERE contact_number = ?\n";

    public static final String DELETE_PHONES_FROM_CUSTOMER =
            "DELETE FROM contact_number\n" +
            "WHERE customer_number = ?\n";

    public static final String PHONE_NUMBER_EXISTS =
            "SELECT contact_number FROM contact_number WHERE contact_number = ?";

}