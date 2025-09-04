package com.telegramService.TelegramBot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegramService.TelegramBot.config.AppConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PollingService {
    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private int lastUpdateId = 0; // Track last processed update

    public PollingService(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @PostConstruct
    public void init() {
        System.out.println("Telegram Polling Service started...");
    }
    @Autowired
    TelegramMessageService telegramMessageService;

    // Run every 2 seconds
    @Scheduled(fixedDelay = 2000)
    public void pollTelegram() {
        try {
            String url = String.format("https://api.telegram.org/bot%s/getUpdates?offset=%d&timeout=5",
                    appConfig.getBotToken(), lastUpdateId + 1);

            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            if (root.get("ok").asBoolean()) {
                for (JsonNode update : root.get("result")) {
                    int updateId = update.get("update_id").asInt();
                    JsonNode message = update.get("message");

                    if (message != null) {
                        long chatId = message.get("chat").get("id").asLong();
                        String text = message.get("text").asText();

                        System.out.println("Received message from chatId=" + chatId + ": " + text);

                        // TODO: call ChatBot service / process user message
                        telegramMessageService.sendMessage(chatId, "You said: " + text);

                        lastUpdateId = updateId; // update offset
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error polling Telegram API: " + e.getMessage());
        }
    }
}