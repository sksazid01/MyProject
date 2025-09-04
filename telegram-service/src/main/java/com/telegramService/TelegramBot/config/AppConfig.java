package com.telegramService.TelegramBot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Getter
@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced   // <-- integrates with Eureka
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${telegram.MotivaMate_bot.token}")
    private String botToken;

}