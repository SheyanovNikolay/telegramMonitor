package ru.iteco.telegrambot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.iteco.telegrambot.demo.service.TelegramBotSender;

/**
 * Обработчик событий готовности приложения
 */
@Component
public class ApplicationStartup {

    @Autowired
    TelegramBotSender telegramBotSender;

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        telegramBotSender.send("Начало работы бота");
    }

    @EventListener
    public void onShutdown(ContextClosedEvent event) {
        telegramBotSender.send("Завершение работы бота");
    }
}