package org.drib.storagebot.util;

import java.security.SecureRandom;

public class SecretTokenUtil {
    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
    private final static SecureRandom RANDOM = new SecureRandom();


    public static String generateToken(int length) {
        if (length < 1 || length > 256) {
            throw new IllegalArgumentException("Token length must be between 1 and 256 characters.");
        }
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(ALLOWED_CHARS.charAt(RANDOM.nextInt(ALLOWED_CHARS.length())));
        }
        return token.toString();
    }
}
