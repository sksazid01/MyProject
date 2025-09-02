package com.telegramService.TelegramBot.controller;


import com.telegramService.TelegramBot.entity.User;
import com.telegramService.TelegramBot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        // TODO: Use the user creation function of chatbot-service
        return userService.createUser(user);
    }
}
