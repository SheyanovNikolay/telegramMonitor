package ru.sheyanovnn.telegrammonitor.bot.handlers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sheyanovnn.telegrammonitor.bot.events.ListenersStatusEvent;

import java.util.Date;

@Service
@EnableScheduling
public class ScheduleService {

    // Время между оповещениями в мс
    private static final long DELAY_TIME = 24 * 60 * 60 * 1000;

    private ApplicationEventPublisher publisher;

    public ScheduleService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = DELAY_TIME)
    public void sendListenersStatus() {
        // TODO здесь вызов метода на сборку инфы о листенерах
        // пока посидит пусть
        Date date = new Date();
        String status = "Status at :" + date;
        publisher.publishEvent(new ListenersStatusEvent(this, status));
    }
}
