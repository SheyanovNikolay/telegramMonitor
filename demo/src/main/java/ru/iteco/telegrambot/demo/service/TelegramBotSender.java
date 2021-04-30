package ru.iteco.telegrambot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.iteco.telegrambot.bot.events.CommandResultEvent;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 29.04.21.
 */

@Component
public class TelegramBotSender {

    @Autowired
    ApplicationEventPublisher publisher;

    public void send(String message){
        publisher.publishEvent(new CommandResultEvent(this, message));

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
