package ru.iteco.telegrambot.bot.handlers.commandhandlers;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface DefaultCommandHandler {
    String handleCommand(Message message);
}
