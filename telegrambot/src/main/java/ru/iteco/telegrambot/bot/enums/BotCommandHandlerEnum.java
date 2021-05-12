package ru.iteco.telegrambot.bot.enums;


import ru.iteco.telegrambot.bot.handlers.commandhandlers.DefaultCommandHandler;
import ru.iteco.telegrambot.bot.handlers.commandhandlers.GetChatIdCommandHandler;
import ru.iteco.telegrambot.bot.handlers.commandhandlers.GetListenersStatusCommandHandler;
import ru.iteco.telegrambot.bot.handlers.commandhandlers.HelpCommandHandler;

/**
 * Перечесление всех комманд, обрабатываемых ботом
 **/
public enum BotCommandHandlerEnum {

    // Без обработчика
    Empty("", null, null),
    // Обработчик команды help
    Help("/help", HelpCommandHandler.class, "Список команд"),
    // Обработчик команды получить id чата
    GetChatId("/get_chat_id", GetChatIdCommandHandler.class, "Получение ID чата"),
    // Обработчик команды получить состояние статусов listener'ов
    GetListenersStatus("/get_listeners_status", GetListenersStatusCommandHandler.class, "Получение статусов работы listener'ов");

    private String command;
    private Class<? extends DefaultCommandHandler> handlerClass;
    private String description;

    <T extends DefaultCommandHandler>
    BotCommandHandlerEnum(String command, Class<T> handlerClass, String description) {
        this.command = command;
        this.handlerClass = handlerClass;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public Class<? extends DefaultCommandHandler> getHandlerClass() {
        return handlerClass;
    }

    public String getDescription() {
        return description;
    }
}
