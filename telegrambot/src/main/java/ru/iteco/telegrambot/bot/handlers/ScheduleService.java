package ru.iteco.telegrambot.bot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import ru.iteco.telegrambot.bot.events.ListenersStatusEvent;

import java.util.Date;

public class ScheduleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(fixedDelayString = "${telegram.delay}")
    public void sendListenersStatus() {
        // TODO здесь вызов метода на сборку инфы о листенерах
        // пока посидит пусть
        Date date = new Date();
        String status = "Status at :" + date;
        publisher.publishEvent(new ListenersStatusEvent(this, status));
    }
}
