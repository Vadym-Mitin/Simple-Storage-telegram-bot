package org.drib.storagebot.util;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SecretTokenUtilTest {


    @Test
    void checkIfTheTokenContainsOnlyAllowedCharacters() {
        String token = SecretTokenUtil.generateToken(128);

        assertEquals(128, token.length());

        Set<Character> characters = SecretTokenUtil.ALLOWED_CHARS.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());


        assertTrue(token.chars().allMatch(c -> characters.contains((char) c)));
    }
}