package org.drib.storagebot;

import org.drib.storagebot.bot.StorageWebHookBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.updatesreceivers.ServerlessWebhook;

@Configuration

public class BotConfig {

    private String botToken;

    @Bean
    public TelegramWebhookBot storageBot() {
        SetWebhook webhookOptions = new SetWebhook();
        DefaultBotOptions botOptions = new DefaultBotOptions();

        return new StorageWebHookBot(botOptions, webhookOptions, botToken);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        ServerlessWebhook webhook = new ServerlessWebhook();
        return new TelegramBotsApi(DefaultBotSession.class, webhook);
    }

}
