package com.telegramService.TelegramBot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ChatService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String generateResponse(String text) {
        Map<String, Object> msgReq = Map.of(
                "username", "sksazid",
                "model", "gemini-2.5-flash",
                "message", text
        );
        try {
            String url = "http://chatbot-service/api/chat";
            String response = restTemplate.postForObject(url, msgReq, String.class);
            JsonNode root = objectMapper.readTree(response);
            return root.get("response").asText();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
