package ru.iteco.telegrambot.bot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import ru.iteco.telegrambot.bot.enums.BotCommandHandlerEnum;
import ru.iteco.telegrambot.bot.events.CommandTaskEvent;

public class ScheduleService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(fixedDelayString = "${telegram.delay}")
    public void sendListenersStatus() {
        publisher.publishEvent(new CommandTaskEvent(this, BotCommandHandlerEnum.GetListenersStatus));
    }
}
