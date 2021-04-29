package ru.iteco.telegrambot.bot.events;

import org.springframework.context.ApplicationEvent;

/**
 * Событие сбора информации о состоянии Listener'ов
 */
public class ListenersStatusEvent extends ApplicationEvent {

    private String listenersStatus;

    public ListenersStatusEvent(Object source, String listenersStatus) {
        super(source);
        this.listenersStatus = listenersStatus;
    }

    public String getListenersStatus() {
        return listenersStatus;
    }
}
