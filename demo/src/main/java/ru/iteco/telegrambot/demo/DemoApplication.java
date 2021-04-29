package ru.iteco.telegrambot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.iteco.telegrambot.demo.service.TelegramBotSender;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 29.04.21.
 */

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
