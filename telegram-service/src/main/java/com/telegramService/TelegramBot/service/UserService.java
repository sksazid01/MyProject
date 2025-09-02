package com.telegramService.TelegramBot.service;


import com.telegramService.TelegramBot.entity.User;
import com.telegramService.TelegramBot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public String createUser(User user) {
        // Use Eureka service-id instead of hardcoding host:port
        String url = "http://chatbot-service/api/users/create";

        return restTemplate.postForObject(url, user, String.class);
    }

//    @Autowired
//    private UserRepository userRepository;
//
//    public String createUser(User user) {
//        try {
//            userRepository.save(user);
//            return "User created successfully with ID: " + user.getId();
//        } catch (Exception e) {
//            return "Error creating user: " + e.getMessage();
//        }
//    }
}