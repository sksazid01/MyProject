#!/usr/bin/env bash

echo "Stopping services..."

# Stop Docker containers if running
if docker ps --format "table {{.Names}}" | grep -E "(discovery-service|chatbot-service|telegram-service)" >/dev/null 2>&1; then
    echo "Stopping Docker containers..."
    docker stop discovery-service chatbot-service telegram-service 2>/dev/null || true
fi

# Stop local Maven processes
echo "Stopping local Maven processes..."
pkill -f 'spring-boot:run.*discovery-service' 2>/dev/null || true
pkill -f 'spring-boot:run.*chatbot-service' 2>/dev/null || true
pkill -f 'spring-boot:run.*telegram-service' 2>/dev/null || true

echo "All services stopped."http://localhost:8761/