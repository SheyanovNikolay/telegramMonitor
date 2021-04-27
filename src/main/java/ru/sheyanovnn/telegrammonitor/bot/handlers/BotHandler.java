package ru.sheyanovnn.telegrammonitor.bot.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sheyanovnn.telegrammonitor.bot.TelegramBot;

/**
 * Класс-обработчик всех процессов телеграм-бота
 */
@Service
public class BotHandler {

    @Value("${telegram.chat.id}")
    private Long chatId;

    private TelegramBot bot;

    public BotHandler(@Lazy TelegramBot bot) {
        this.bot = bot;
    }

    /**
     * Обработка сообщения, написанного в чат
     */
    public void handleUpdate(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            handleInputMessage(message);
        }
    }

    private void handleInputMessage(Message message) {
        SendMessage replyMessage = new SendMessage().setChatId(message.getChatId());
        replyMessage.setText("Ты написал " + message.getText());
        sending(replyMessage);
    }

    /**
     * Отправка сообщения о статусах Listener'ов в групповой чат
     */
    public void sendListenersStatus(String status) {
        SendMessage sendMessage = new SendMessage().setChatId(chatId);
        sendMessage.setText(status);
        sending(sendMessage);
    }

    private void sending(SendMessage message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException ex) {
            //TODO  Переделать на логгирование
            System.out.println(ex);
        }
    }
}
