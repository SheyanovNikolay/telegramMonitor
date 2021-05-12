package ru.iteco.telegrambot.bot.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.iteco.telegrambot.bot.enums.BotCommandHandlerEnum;

import java.util.stream.Stream;

@Service
public class BotCommandEnumService {

    @Value("${telegram.username}")
    private String username;

    /**
     * Получение информации о обработчике команды телеграм бота
     * @param command - текстовое представление команды
     * @return Данные обработчика
     */
    public BotCommandHandlerEnum getHandler(String command) {
        return Stream.of(BotCommandHandlerEnum.values())
                .filter(e -> equalsCommand(e, command))
                .findFirst().orElse(BotCommandHandlerEnum.Empty);
    }

    /**
     * Проверка команды написанной в чат, включая непосредственное обращение к боту
     * @param command - текстовое представление команды
     */
    private boolean equalsCommand(BotCommandHandlerEnum commandEnum, String command) {
        String namedHandlerCommand = commandEnum.getCommand() + "@" + username;
        return commandEnum.getCommand().equals(command) || namedHandlerCommand.equals(command);
    }
}
