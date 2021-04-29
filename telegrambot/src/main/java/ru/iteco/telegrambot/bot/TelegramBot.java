package ru.iteco.telegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.iteco.telegrambot.bot.events.ListenersStatusEvent;
import ru.iteco.telegrambot.bot.handlers.BotHandler;

/**
 * Класс, реализующий телеграм бота
 * Главный listener событий телеграм бота
 */
@Component
public class TelegramBot extends TelegramLongPollingBot implements ApplicationListener<ListenersStatusEvent> {

    @Value("${telegram.username}")
    private String username;

    @Value("${telegram.token}")
    private String token;

    private BotHandler botHandler;

    public TelegramBot(BotHandler botHandler) {
        this.botHandler = botHandler;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    /**
     * Прослушивание сообщения, написанного в чат и дальнейшая передача в его обработку
     * @param update - событие, содержащее всю информацию о чате, пользователе и сообщении
     */
    @Override
    public void onUpdateReceived(Update update) {
        botHandler.handleUpdate(update);
    }

    /**
     * Прослушивание сообщения от Scheduler'а,
     * на возникновение события сбора информации о статусах Listener'ов
     * @param listenersStatusEvent - событие, содержащее информацию о статусах Listener'ов
     */
    @Override
    public void onApplicationEvent(@NonNull ListenersStatusEvent listenersStatusEvent) {
        botHandler.sendListenersStatus(listenersStatusEvent.getListenersStatus());
    }
}
