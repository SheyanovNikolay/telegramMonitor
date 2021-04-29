package ru.iteco.telegrambot.bot.events;

import org.springframework.context.ApplicationEvent;

/**
 * Событие, описывающее результат выполнения команд,
 * не относящихся к вспомогательным командам бота
 */
public class CommandResultEvent extends ApplicationEvent {

    private String commandResult;

    public CommandResultEvent(Object source, String commandResult) {
        super(source);
        this.commandResult = commandResult;
    }

    public String getCommandResult() {
        return commandResult;
    }
}
