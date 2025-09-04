#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT"

echo "Building all services..."
./mvnw clean install -DskipTests

sleep 1
echo "Starting Discovery Service..."
gnome-terminal -- bash -c "cd \"$ROOT\" && ./mvnw spring-boot:run -pl discovery-service; exec bash"

sleep 1
echo "Starting Chatbot Service with environment variables..."
gnome-terminal -- bash -c "cd \"$ROOT\" && export \$(grep -v '^#' .env | grep -v '^\$' | xargs) && ./mvnw spring-boot:run -pl chatbot-service; exec bash"

sleep 1
echo "Starting Telegram Service with environment variables..."
gnome-terminal -- bash -c "cd \"$ROOT\" && export \$(grep -v '^#' .env | grep -v '^\$' | xargs) && ./mvnw spring-boot:run -pl telegram-service; exec bash"

echo ""
echo "✅ All services are starting up in separate terminals!"
echo ""
echo "Service URLs:"
echo "  🔍 Discovery Service (Eureka): http://localhost:8761"
echo "  🤖 Chatbot Service API:       http://localhost:8080"
echo "  📱 Telegram Service:          http://localhost:8081"
echo ""
echo "Checking service status..."
echo ""

# Function to check if service is running
check_service() {
    local service_name="$1"
    local url="$2"
    local max_attempts=10
    local attempt=1
    
    while [ $attempt -le $max_attempts ]; do
        if curl -s "$url" > /dev/null 2>&1; then
            echo "✅ $service_name is UP - $url"
            return 0
        fi
        echo "⏳ Waiting for $service_name... (attempt $attempt/$max_attempts)"
        sleep 2
        attempt=$((attempt + 1))
    done
    echo "❌ $service_name failed to start - $url"
    return 1
}

# Check each service
check_service "Discovery Service (Eureka)" "http://localhost:8761"
check_service "Chatbot Service" "http://localhost:8080/actuator/health"
check_service "Telegram Service" "http://localhost:8081/actuator/health"

echo ""
echo "Service URLs:"
echo "  🔍 Discovery Service (Eureka): http://localhost:8761"
echo "  🤖 Chatbot Service API:       http://localhost:8080"
echo "  📱 Telegram Service:          http://localhost:8081"
echo ""
echo "To stop all services, run: ./stop-services.sh"