package ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.sheyanovnn.telegrammonitor.bot.enums.BotCommandHandlerEnum;

import java.util.StringJoiner;
import java.util.stream.Stream;

@Service
public class HelpCommandHandler implements DefaultCommandHandler {

    @Override
    public String handleCommand(Message message) {
        StringJoiner listCommand = new StringJoiner("\n");
        Stream.of(BotCommandHandlerEnum.values())
                .filter(b -> !b.equals(BotCommandHandlerEnum.Empty))
                .forEach(b -> listCommand.add(b.getCommand() + " - " + b.getDescription()));
        return listCommand.toString();
    }
}
