package org.drib.storagebot.security;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Getter
public class BotUrlSecret {
    private String token;

    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
    private final SecureRandom RANDOM = new SecureRandom();

    public BotUrlSecret() {
        this.token = generateToken(128);
    }

    public String generateToken(int length) {
        if (length < 1 || length > 256) {
            throw new IllegalArgumentException("Token length must be between 1 and 256 characters.");
        }
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(ALLOWED_CHARS.charAt(RANDOM.nextInt(ALLOWED_CHARS.length())));
        }
        return token.toString();
    }

    public String refreshToken() {
        this.token = generateToken(128);
        return token;
    }
}
