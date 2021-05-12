package ru.iteco.telegrambot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.iteco.telegrambot.bot.enums.BotCommandHandlerEnum;
import ru.iteco.telegrambot.bot.events.CommandResultEvent;
import ru.iteco.telegrambot.bot.events.CommandTaskEvent;

import java.util.Date;

@Service
public class TestGetListenersStatusCommandHandler implements DefaultCommandHandler, ApplicationListener<CommandTaskEvent> {

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public String handleCommand() {
        Date date = new Date();
        return "Status at :" + date;
    }

    @Override
    public void onApplicationEvent(@NonNull CommandTaskEvent commandTaskEvent) {
        if (commandTaskEvent.getCommand().equals(BotCommandHandlerEnum.GetListenersStatus)) {
            String result = handleCommand();
            publisher.publishEvent(new CommandResultEvent(this, result));
        }
    }

}
