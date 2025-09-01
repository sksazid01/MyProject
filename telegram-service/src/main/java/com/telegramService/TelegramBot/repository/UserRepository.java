package com.telegramService.TelegramBot.repository;


import com.telegramService.TelegramBot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
}
