package com.telegramService.TelegramBot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    @Autowired
    ChatService chatService;

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

                    // TODO: call ChatBot service / process user message
                    if (message != null) {
                        long chatId = message.get("chat").get("id").asLong();
                        String text = message.get("text").asText();


//                        String data = message.get("data").asText();

//                        if (data.equals("ai_friend")) {
                        telegramMessageService.sendMessage(chatId,"Just a moment, I’m preparing your reply...");
                        String reply = chatService.generateResponse(text);
                        if (reply != null) {
                            telegramMessageService.sendMessage(chatId, reply);
                        } else {
                            telegramMessageService.sendMessage(chatId, "Sorry, I couldn't process your request at the moment, please try again.");
                        }

                        System.out.println("Received message from chatId=" + chatId + ": " + text);

                        lastUpdateId = updateId; // update offset
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}