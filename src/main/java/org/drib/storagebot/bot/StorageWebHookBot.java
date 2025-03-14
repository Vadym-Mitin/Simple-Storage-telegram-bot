package org.drib.storagebot.bot;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@Getter
public class StorageWebHookBot extends SpringWebhookBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.path}")
    private String botPath;


    public StorageWebHookBot(DefaultBotOptions options, SetWebhook setWebhook, String botToken) {
        super(options, setWebhook, botToken);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.debug("Receive update {}", update);
        //not implemented yet
        return null;
    }

}
