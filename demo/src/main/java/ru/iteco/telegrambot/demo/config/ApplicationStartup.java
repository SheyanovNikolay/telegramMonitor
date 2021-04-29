package ru.iteco.telegrambot.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.iteco.telegrambot.demo.service.TelegramBotSender;

/**
 * Обработчик событий готовности приложения и закрытия контекста
 */

@Component
//@Log4j2(topic = "system")
public class ApplicationStartup {

    /**
     * Слушатель готовности приложения
     *
     * @param event событие готовности приложения
     */

    @Autowired
    TelegramBotSender telegramBotSender;

    @EventListener
    public void onStartup(ApplicationReadyEvent event) {
        telegramBotSender.send();
    }


}