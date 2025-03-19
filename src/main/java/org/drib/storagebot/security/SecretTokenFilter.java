package org.drib.storagebot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
@Profile("test")
public class SecretTokenFilter extends OncePerRequestFilter {

    @Autowired
    private final BotUrlSecret secretToken;

    public static final String X_TELEGRAM_BOT_API_SECRET_TOKEN = "x-telegram-bot-api-secret-token";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        ofNullable(request.getHeader(X_TELEGRAM_BOT_API_SECRET_TOKEN))
                .filter(s -> s.equals(secretToken.getToken()))
                .orElseThrow(() -> new ServletException("Invalid secret token"));

        chain.doFilter(request, response);
    }

}
