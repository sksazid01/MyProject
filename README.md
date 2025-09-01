# Microservices Architecture

This project contains a Spring Boot microservices architecture with service discovery using Netflix Eureka.

## Services Overview

### 🔍 Discovery Service (Port 8761)
- **Purpose**: Eureka Server for service discovery and registration
- **Technology**: Spring Cloud Netflix Eureka Server
- **URL**: http://localhost:8761
- **Dashboard**: http://localhost:8761 (Eureka Management Interface)

### 💬 Chatbot Service
- **Purpose**: AI-powered chatbot service using Gemini API
- **Technology**: Spring Boot, Gemini AI Integration
- **Features**: Chat processing, user management, chat history

### 📱 Telegram Service  
- **Purpose**: Telegram bot integration service
- **Technology**: Spring Boot, Telegram Bot API
- **Features**: Message handling, user management, health monitoring

## Architecture Features

- **Service Discovery**: All services register with Eureka Discovery Service
- **Microservices Pattern**: Independent, deployable services
- **Load Balancing**: Built-in client-side load balancing
- **Health Monitoring**: Service health checks via Eureka
- **Centralized Configuration**: Environment-based configuration

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Running the Services

1. **Start Discovery Service** (Required first):
```bash
cd discovery-service
./mvnw spring-boot:run
```

2. **Start Chatbot Service**:
```bash
cd chatbot-service
./mvnw spring-boot:run
```

3. **Start Telegram Service**:
```bash
cd telegram-service
./mvnw spring-boot:run
```

### Service Endpoints

| Service | Port | Health Check | Dashboard |
|---------|------|--------------|-----------|
| Discovery Service | 8761 | http://localhost:8761/actuator/health | http://localhost:8761 |
| Chatbot Service | TBD | /actuator/health | - |
| Telegram Service | TBD | /actuator/health | - |

## File Structure

```
├── discovery-service/           # Eureka Discovery Server
│   ├── src/main/java/com/discovery/discoveryservice/
│   │   └── DiscoveryServiceApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
├── chatbot-service/            # AI Chatbot Service
│   ├── src/main/java/com/ChatBot/chatbot_service/
│   │   ├── ChatBotServiceApplication.java
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── repository/
│   │   └── service/
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
├── telegram-service/           # Telegram Bot Service
│   ├── src/main/java/com/telegramService/TelegramBot/
│   │   ├── TelegramBotApplication.java
│   │   ├── controller/
│   │   ├── entity/
│   │   ├── repository/
│   │   └── service/
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
├── .gitignore                  # Root gitignore
└── README.md                   # This file
```
