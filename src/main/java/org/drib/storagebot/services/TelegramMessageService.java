package org.drib.storagebot.services;

import org.drib.storagebot.bot.StorageWebHookBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Service
public class TelegramMessageService {
    @Autowired
    StorageWebHookBot bot;
    @Autowired
    TelegramBotsApi botsApi;


    public void sendMessage(String message) {
//        bot.execute()
    }

}
