package ru.iteco.telegrambot.bot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.iteco.telegrambot.bot.TelegramBot;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(value = {"ru.iteco.telegrambot"})
@ConditionalOnProperty(value = "telegram.main.enabled")
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