package com.pansauce.constants.query;

public class PhoneQuery {
    public static final String GET_ALL_PHONES_BY_CUSTOMER_KEY = "SELECT contact_number FROM Contact_Number WHERE customer_id = ?";
    public static final String ADD_PHONE_TO_CUSTOMER_BY_KEY = "INSERT INTO contact_number " +
                                                        "(contact_number, customer_number) VALUES (?, ?)";
}