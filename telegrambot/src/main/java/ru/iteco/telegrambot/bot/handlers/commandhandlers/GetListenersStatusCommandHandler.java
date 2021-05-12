package ru.iteco.telegrambot.bot.handlers.commandhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.iteco.telegrambot.bot.enums.BotCommandHandlerEnum;
import ru.iteco.telegrambot.bot.events.CommandTaskEvent;

@Service
public class GetListenersStatusCommandHandler implements DefaultCommandHandler {

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public String handleCommand(Message message) {
        publisher.publishEvent(new CommandTaskEvent(this, BotCommandHandlerEnum.GetListenersStatus));
        return null;
    }
}
