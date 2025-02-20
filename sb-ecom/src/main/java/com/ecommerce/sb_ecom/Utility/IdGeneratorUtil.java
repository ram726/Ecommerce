package com.ecommerce.sb_ecom.Utility;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdGeneratorUtil {

    private static final int ID_LENGTH = 25; // Default ID length
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Alphanumeric characters
    private static final SecureRandom RANDOM = new SecureRandom();

    private IdGeneratorUtil() {
        // Private constructor to prevent instantiation
    }

    public static String generateId() {
        // Using Java 8 Streams to generate a random alphanumeric string efficiently
        String randomString = IntStream.range(0, ID_LENGTH)
                .mapToObj(i -> String.valueOf(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()))))
                .collect(Collectors.joining());

        return "ECOM" + randomString;
    }
}
