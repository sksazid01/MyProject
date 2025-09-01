# File Structure

```   
   ├── .env
│   ├── .gitattributes
│   ├── .gitignore
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── README.md
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── ChatBot
│   │   │   │           └── chatbot_service
│   │   │   │               ├── ChatBotServiceApplication.java
│   │   │   │               ├── config
│   │   │   │               │   └── ExternalApiProperties.java
│   │   │   │               ├── controller
│   │   │   │               │   ├── ChatController.java
│   │   │   │               │   └── UserController.java
│   │   │   │               ├── dto
│   │   │   │               │   ├── ChatRequest.java
│   │   │   │               │   └── ChatResponse.java
│   │   │   │               ├── entity
│   │   │   │               │   ├── ChatHistory.java
│   │   │   │               │   └── User.java
│   │   │   │               ├── repository
│   │   │   │               │   ├── ChatHistoryRepository.java
│   │   │   │               │   └── UserRepository.java
│   │   │   │               └── service
│   │   │   │                   ├── ChatService.java
│   │   │   │                   ├── GeminiService.java
│   │   │   │                   └── UserService.java
│   │   │   └── resources
│   │   │       ├── application.yml
│   │   │       └── templates
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── ChatBot
│   │                   └── chatbot_service
│   │                       └── ChatBotServiceApplicationTests.java
│   └── .vscode
│       └── settings.json
├── .gitignore
└── telegram-service
    ├── .env
    ├── .gitattributes
    ├── .gitignore
    ├── HELP.md
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── telegramService
    │   │   │           └── TelegramBot
    │   │   │               ├── controller
    │   │   │               │   ├── healthController.java
    │   │   │               │   ├── msgController.java
    │   │   │               │   └── UserController.java
    │   │   │               ├── dto
    │   │   │               ├── entity
    │   │   │               │   ├── ChatHistory.java
    │   │   │               │   └── User.java
    │   │   │               ├── repository
    │   │   │               │   └── UserRepository.java
    │   │   │               ├── service
    │   │   │               │   └── UserService.java
    │   │   │               └── TelegramBotApplication.java
    │   │   └── resources
    │   │       ├── application.yml
    │   │       └── templates
    │   └── test
    │       └── java
    │           └── com
    │               └── telegramService
    │                   └── TelegramBot
    │                       └── TelegramBotApplicationTests.java
    └── .vscode
        └── settings.json
```
