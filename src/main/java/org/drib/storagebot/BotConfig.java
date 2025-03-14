package org.drib.storagebot;

import org.drib.storagebot.bot.StorageWebHookBot;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.url}")
    private String botWebhookUrl;
    @Value("${telegram.bot.url.secret}")
    private String botWebhookUrlSecret;

    @Bean
    public SetWebhook webhookOptions() {
        return SetWebhook.builder()
                .url(botWebhookUrl)
                .secretToken(botWebhookUrlSecret)
//                .allowedUpdates()
//                .certificate()
                .build();
    }

    @Bean
    public TelegramWebhookBot storageBot(SetWebhook webhookOptions) {
        DefaultBotOptions botOptions = new DefaultBotOptions();

        return new StorageWebHookBot(botOptions, webhookOptions, botToken);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        ServerlessWebhook webhook = new ServerlessWebhook();
        return new TelegramBotsApi(DefaultBotSession.class, webhook);
    }

}
