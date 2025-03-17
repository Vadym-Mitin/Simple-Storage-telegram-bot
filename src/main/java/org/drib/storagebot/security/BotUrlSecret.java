package org.drib.storagebot.security;

import lombok.Getter;
import org.drib.storagebot.util.SecretTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotUrlSecret {
    @Value("${telegram.bot.url.secret}")
    private String token;

    public String refreshToken() {
        this.token = SecretTokenUtil.generateToken(128);
        return token;
    }
}
