package org.drib.storagebot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.drib.storagebot.util.SecretTokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/v1/mock")
public class MockController {

    private static String id;

    public MockController() {
        id = SecretTokenUtil.generateToken(10);
    }

    @GetMapping("/whoami")
    public String whoAmI() {
        log.debug("/whoami was called");
        return "I am the %s".formatted(id);
    }

    @GetMapping("/env")
    public Map<String, String> getEnvironmentVariables() {
        Map<String, String> getenv = System.getenv();
        String appServiceTelegramBotWebhookUrl = System.getenv("APP_SERVICE_TELEGRAM_BOT_WEBHOOK_URL");
        getenv.put("App Service Telegram Bot Webhook Url Environment Variable", appServiceTelegramBotWebhookUrl);
        return getenv;
    }
}
