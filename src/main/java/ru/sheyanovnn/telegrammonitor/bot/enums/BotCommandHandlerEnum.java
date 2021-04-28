package ru.sheyanovnn.telegrammonitor.bot.enums;


import ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers.DefaultCommandHandler;
import ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers.GetChatIdHandler;

import java.util.stream.Stream;

public enum BotCommandHandlerEnum {

    // Без обработчика
    Empty("", null),
    // Обработчик команды получить id группового чата
    GetChatId("/get_chat_id", GetChatIdHandler.class);

    private static final String BOT_USERNAME = "@SandboxMonitorBot";
    String command;
    Class<? extends DefaultCommandHandler> handlerClass;

    <T extends DefaultCommandHandler>
    BotCommandHandlerEnum(String command, Class<T> handlerClass) {
        this.command = command;
        this.handlerClass = handlerClass;
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

    /**
     * Проверка команды написанной в чат, а также с непосредственным обращением к боту
     * @param command - текстовое представление команды
     */
    private boolean equalsCommand(String command) {
        String namedHandlerCommand = this.command + BOT_USERNAME;
        return this.command.equals(command) || namedHandlerCommand.equals(command);
    }
}
