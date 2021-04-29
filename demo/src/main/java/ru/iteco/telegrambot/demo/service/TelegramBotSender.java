package ru.iteco.telegrambot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.sheyanovnn.telegrammonitor.bot.events.ListenersStatusEvent;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 29.04.21.
 */

@Component
public class TelegramBotSender {

    @Autowired
    ApplicationEventPublisher publisher;

    public void send(){
        publisher.publishEvent(new ListenersStatusEvent(this, "Test demo message!!!"));
    }
}
