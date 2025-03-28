package org.drib.storagebot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.drib.storagebot.util.SecretTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

@Slf4j
@RestController
@RequestMapping("/v1/mock")
public class MockController {

    private static String id;

    @Value("${telegram.bot.url}")
    private String telegramBotUrl;

    public MockController() {
        id = SecretTokenUtil.generateToken(10);
    }

    @RequestMapping(path = "/whoami", method = {GET, HEAD})
    public String whoAmI() {
        log.debug("/whoami was called");
        return "I am the %s".formatted(id);
    }

    @GetMapping("/env")
    public Map<String, String> getEnvironmentVariables() {
        Map<String, String> getenv = new HashMap<>(System.getenv());
        String appServiceTelegramBotWebhookUrl = System.getenv("APP_SERVICE_TELEGRAM_BOT_WEBHOOK_URL");
        getenv.put("App Service Telegram Bot Webhook Url Environment Variable", appServiceTelegramBotWebhookUrl);
        getenv.put("App Service Telegram Bot Webhook Url System Property Variable", telegramBotUrl);

        return getenv;
    }
}
