#!/usr/bin/env bash
# filepath: /home/sk-sazid/Desktop/Project/stop-services.sh

echo "Stopping Discovery Service..."
pkill -f 'spring-boot:run.*discovery-service' || true

echo "Stopping Chatbot Service..."
pkill -f 'spring-boot:run.*chatbot-service' || true

echo "Stopping Telegram Service..."
pkill -f 'spring-boot:run.*telegram-service' || true

echo "All services stopped."http://localhost:8761/