package com.telegramService.TelegramBot.service;


import com.telegramService.TelegramBot.entity.User;
import com.telegramService.TelegramBot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public String createUser(User user) {
        try {
            // Check if user already exists
            User existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser != null) {
                return "User with username '" + user.getUsername() + "' already exists";
            }
            
            System.out.println("Creating user: " + user.getUsername() + " with email: " + user.getEmail());
            User savedUser = userRepository.save(user);
            System.out.println("User saved with ID: " + savedUser.getId());
            return "User created successfully";
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            return "Error creating user: " + e.getMessage();
        }
    }
}