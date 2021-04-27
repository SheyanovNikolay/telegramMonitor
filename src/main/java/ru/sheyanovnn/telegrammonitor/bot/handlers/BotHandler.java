package ru.sheyanovnn.telegrammonitor.bot.handlers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sheyanovnn.telegrammonitor.bot.TelegramBot;
import ru.sheyanovnn.telegrammonitor.bot.enums.BotCommandHandlerEnum;
import ru.sheyanovnn.telegrammonitor.bot.handlers.commandhandlers.DefaultCommandHandler;

/**
 * Класс-обработчик всех процессов телеграм-бота
 */
@Service
@Log4j2(topic = "telegram-bot")
public class BotHandler {

    @Value("${telegram.chat.id}")
    private Long chatId;

    private TelegramBot bot;
    private ApplicationContext context;

    public BotHandler(@Lazy TelegramBot bot, ApplicationContext context) {
        this.bot = bot;
        this.context = context;
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

    /**
     * Обработка команд телеграм бота
     * */
    private void handleInputMessage(Message message) {
        BotCommandHandlerEnum handlerEnum = BotCommandHandlerEnum.getHandler(message.getText());

        // Если входящее сообщение не является ни одной из обрабатываемых команд
        if (handlerEnum.equals(BotCommandHandlerEnum.Empty)) {
            DeleteMessage deleteMessage = new DeleteMessage().setChatId(chatId).setMessageId(message.getMessageId());
            deleting(deleteMessage);
            return;
        }

        // Выполнение команды и передача результата выполнения в виде сообщения в чат
        try {
            DefaultCommandHandler handler = context.getBean(handlerEnum.getHandlerClass());
            String handlingResult = handler.handleCommand(message);
            SendMessage replyMessage = new SendMessage().setChatId(message.getChatId());
            replyMessage.setText(handlingResult);
            sending(replyMessage);
        } catch (Exception e) {
            log.error(e);
        }
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
            log.error("Error while sending message: " + ex);
        }
    }

    private void deleting(DeleteMessage deleteMessage) {
        try {
            bot.execute(deleteMessage);
        } catch (TelegramApiException ex) {
            log.error("Error while deleting message: " + ex);
        }
    }
}
