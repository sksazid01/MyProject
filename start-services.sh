#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT"

echo "Building all services..."
./mvnw clean install -DskipTests

echo "Starting Discovery Service..."
gnome-terminal -- bash -c "cd '$ROOT' && ./mvnw spring-boot:run -pl discovery-service; exec bash"

sleep 10

echo "Starting Chatbot Service..."
gnome-terminal -- bash -c "cd '$ROOT' && ./mvnw spring-boot:run -pl chatbot-service; exec bash"

echo "Starting Telegram Service..."
gnome-terminal -- bash -c "cd '$ROOT' && ./mvnw spring-boot:run -pl telegram-service; exec bash"