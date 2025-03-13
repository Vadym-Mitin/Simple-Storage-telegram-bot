package org.drib.storagebot.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(Constants.WEBHOOK_URL_PATH)
@RequiredArgsConstructor
public class WebhookBotController {

    @Autowired
    private final Set<TelegramWebhookBot> bots;

    private final ConcurrentHashMap<String, TelegramWebhookBot> callbacks = new ConcurrentHashMap<>();

    @PostConstruct
    public void registerCallback() {
        bots.forEach(bot -> callbacks.put(bot.getBotPath(), bot));
    }

    @PostMapping(path = "/{botPath}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Response updateReceived(@RequestParam("botPath") String botPath, @RequestBody Update update) {
        if (callbacks.containsKey(botPath)) {
            try {
                BotApiMethod<?> response = callbacks.get(botPath).onWebhookUpdateReceived(update);
                if (response != null) {
                    response.validate();
                }
                return Response.ok(response).build();
            } catch (TelegramApiValidationException e) {
                log.error(e.getLocalizedMessage(), e);
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GetMapping(path = "/{botPath}", produces = APPLICATION_JSON_VALUE)
    public String testReceived(@RequestParam("botPath") String botPath) {
        if (callbacks.containsKey(botPath)) {
            return "Hi there " + botPath + "!";
        } else {
            return "Callback not found for " + botPath;
        }
    }
}
