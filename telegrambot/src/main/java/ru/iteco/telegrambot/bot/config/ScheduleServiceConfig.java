package ru.iteco.telegrambot.bot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.iteco.telegrambot.bot.handlers.ScheduleService;

@Configuration
@EnableScheduling
public class ScheduleServiceConfig {

    @Bean
    @ConditionalOnProperty(value = "telegram.enabled")
    public ScheduleService scheduleService() {
        return new ScheduleService();
    }
}
