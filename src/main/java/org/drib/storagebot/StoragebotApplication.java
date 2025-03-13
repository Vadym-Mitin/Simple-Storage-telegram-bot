package org.drib.storagebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

@SpringBootApplication
@ComponentScan(
        basePackages = {"org.drib.storagebot", "org.telegram.telegrambots", "org.telegram.telegrambots.starter"}
)
public class StoragebotApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoragebotApplication.class, args);
    }

}
