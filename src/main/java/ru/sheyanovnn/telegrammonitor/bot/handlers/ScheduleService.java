package ru.sheyanovnn.telegrammonitor.bot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import ru.sheyanovnn.telegrammonitor.bot.events.ListenersStatusEvent;

import java.util.Date;
import java.util.Map;

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

    public abstract Map<String, Boolean> checkListeners()
}
