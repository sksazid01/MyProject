package com.telegramService.TelegramBot.service;

import com.telegramService.TelegramBot.config.AppConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramMessageService {

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    public TelegramMessageService(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.restTemplate = new RestTemplate();
    }

    public void sendMessage(long chatId, String text) {
        String url = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%d&text=%s",
                appConfig.getBotToken(), chatId, text
        );
        restTemplate.getForObject(url, String.class);
    }
}