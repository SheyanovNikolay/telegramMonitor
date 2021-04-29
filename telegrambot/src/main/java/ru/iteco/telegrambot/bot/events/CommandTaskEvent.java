package ru.iteco.telegrambot.bot.events;

import org.springframework.context.ApplicationEvent;
import ru.iteco.telegrambot.bot.enums.BotCommandHandlerEnum;

/**
 * Событие, описывающее задание на выполнение команды
 * в удаленный обработчик
 */
public class CommandTaskEvent extends ApplicationEvent {

    private BotCommandHandlerEnum command;

    public CommandTaskEvent(Object source, BotCommandHandlerEnum command) {
        super(source);
        this.command = command;
    }

    public BotCommandHandlerEnum getCommand() {
        return command;
    }
}
