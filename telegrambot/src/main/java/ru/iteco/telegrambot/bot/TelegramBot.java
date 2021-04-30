package ru.iteco.telegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.iteco.telegrambot.bot.events.CommandResultEvent;
import ru.iteco.telegrambot.bot.handlers.BotHandler;

/**
 * Класс, реализующий телеграм бота
 * Главный listener событий телеграм бота
 */
public class TelegramBot extends TelegramLongPollingBot implements ApplicationListener<CommandResultEvent> {

    @Value("${telegram.username}")
    private String username;

    @Value("${telegram.token}")
    private String token;

    @Autowired
    private BotHandler botHandler;

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
     * Прослушивание сообщений о выполнении обработки команд
     * @param commandResultEvent - событие, содержащее информацию о результатах выполнении команды
     */
    @Override
    public void onApplicationEvent(@NonNull CommandResultEvent commandResultEvent) {
        botHandler.sendCommandResult(commandResultEvent.getCommandResult());
    }
}
