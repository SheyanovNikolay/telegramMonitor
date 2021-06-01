package ru.iteco.telegrambot.bot.handlers.commandhandlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class StartCommandHandler implements DefaultCommandHandler {

    @Override
    public String handleCommand(Message message) {
        return "Начало работы бота.";
    }
}
