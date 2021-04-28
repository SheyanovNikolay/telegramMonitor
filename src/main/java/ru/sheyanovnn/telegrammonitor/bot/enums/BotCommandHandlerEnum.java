package ru.sheyanovnn.telegrammonitor.bot.enums;


import ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers.DefaultCommandHandler;
import ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers.GetChatIdCommandHandler;
import ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers.HelpCommandHandler;

import java.util.stream.Stream;

public enum BotCommandHandlerEnum {

    // Без обработчика
    Empty("", null, null),
    // Обработчик команды help
    Help("/help", HelpCommandHandler.class, "Список команд"),
    // Обработчик команды получить id чата
    GetChatId("/get_chat_id", GetChatIdCommandHandler.class, "Получение ID чата");

    private static final String BOT_USERNAME = "@SandboxMonitorBot";
    private String command;
    private Class<? extends DefaultCommandHandler> handlerClass;
    private String description;

    <T extends DefaultCommandHandler>
    BotCommandHandlerEnum(String command, Class<T> handlerClass, String description) {
        this.command = command;
        this.handlerClass = handlerClass;
        this.description = description;
    }

    /**
     * Получение информации о обработчике команды телеграм бота
     * @param command - текстовое представление команды
     * @return Данные обработчика
     */
    public static BotCommandHandlerEnum getHandler(String command) {
        return Stream.of(BotCommandHandlerEnum.values())
                .filter(e -> e.equalsCommand(command))
                .findFirst().orElse(Empty);
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

    /**
     * Проверка команды написанной в чат, включая непосредственное обращение к боту
     * @param command - текстовое представление команды
     */
    private boolean equalsCommand(String command) {
        String namedHandlerCommand = this.command + BOT_USERNAME;
        return this.command.equals(command) || namedHandlerCommand.equals(command);
    }
}
