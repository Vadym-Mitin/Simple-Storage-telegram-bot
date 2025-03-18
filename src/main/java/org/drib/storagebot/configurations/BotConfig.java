package org.drib.storagebot.configurations;

import org.drib.storagebot.bot.StorageWebHookBot;
import org.drib.storagebot.security.BotUrlSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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


    @Bean
    public SetWebhook webhookOptions(BotUrlSecret botUrlSecret) {
        return SetWebhook.builder()
                .url(botWebhookUrl)
                .secretToken(botUrlSecret.getToken())
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
    @Profile("!test")
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        ServerlessWebhook webhook = new ServerlessWebhook();
        return new TelegramBotsApi(DefaultBotSession.class);
//        return new TelegramBotsApi(DefaultBotSession.class, webhook);
    }

}
