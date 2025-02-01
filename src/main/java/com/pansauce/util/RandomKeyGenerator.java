package com.pansauce.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomKeyGenerator {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase();
    public static final String digits = "0123456789";
    public static final String alphanumeric = upper + lower + digits;

    private static final Random random = new SecureRandom();
    private final char[] symbols;
    private final char[] buffer;

    public RandomKeyGenerator(int length, String symbols) {
        this.symbols = symbols.toCharArray();
        this.buffer = new char[length];
    }

    public RandomKeyGenerator(int length) {
        this(length, alphanumeric);
    }

    public String nextString() {
        for (int i = 0; i < buffer.length; ++i)
            buffer[i] = symbols[random.nextInt(symbols.length)];
        return new String(buffer);
    }

}