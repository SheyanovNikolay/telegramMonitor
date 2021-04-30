package ru.iteco.telegrambot.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.iteco.telegrambot.bot.TelegramBot;

import javax.annotation.PostConstruct;

@Configuration
public class BotConfig {

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
    }

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot();
    }
}
