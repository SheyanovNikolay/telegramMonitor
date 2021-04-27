package ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class GetChatIdHandler implements DefaultCommandHandler {

    @Override
    public String handleCommand(Message message) {
        return "Chat ID: " + message.getChatId().toString();
    }
}
