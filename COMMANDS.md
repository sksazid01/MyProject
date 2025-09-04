# Microservices Commands Cheat Sheet

Quick, copy‑pasteable commands you used and will use to build, run, check, and troubleshoot the services.

Ports
- Eureka (discovery-service): 8761
- Chatbot (chatbot-service): 8080
- Telegram (telegram-service): 8081

## Prerequisites
- Java 21 and Maven Wrapper (`./mvnw`) available
- Optional: `gnome-terminal` (script falls back to `nohup` + logs if missing)

## Environment (.env)
Used by chatbot and telegram services (database/API keys). Keep `.env` in the project root.
Safely load into your shell when running a single service:
```bash
set -a
[ -f .env ] && . ./.env
set +a
```

## Build
```bash
./mvnw clean install -DskipTests
```

## Start all services (recommended)
```bash
./start-services.sh
```
Notes
- Loads `.env` for chatbot/telegram
- Quieter logs (ERROR only)
- Health checks run automatically
- If no `gnome-terminal`, logs go to `logs/*.log`

## Stop all services
```bash
./stop-services.sh
# If anything lingers:
pkill -f "mvnw spring-boot:run"
```

## Start services individually (quiet mode)
### Discovery Service
```bash
./mvnw spring-boot:run -pl discovery-service -B -q \
  -Dspring.profiles.active=quiet \
  -Dspring.main.banner-mode=off \
  -Dspring.output.ansi.enabled=never \
  -Dspring.devtools.add-properties=false \
  -Dlogging.level.root=ERROR \
  -Dlogging.level.org.springframework=ERROR \
  -Dlogging.level.org.apache=ERROR \
  -Dlogging.level.com.netflix=ERROR \
  -Dlogging.level.org.hibernate=ERROR \
  -Dlogging.level.com.zaxxer.hikari=ERROR
```

### Chatbot Service
```bash
set -a; [ -f .env ] && . ./.env; set +a
./mvnw spring-boot:run -pl chatbot-service -B -q \
  -Dspring.profiles.active=quiet \
  -Dspring.main.banner-mode=off \
  -Dspring.output.ansi.enabled=never \
  -Dspring.devtools.add-properties=false \
  -Dlogging.level.root=ERROR \
  -Dlogging.level.org.springframework=ERROR \
  -Dlogging.level.org.apache=ERROR \
  -Dlogging.level.com.netflix=ERROR \
  -Dlogging.level.org.hibernate=ERROR \
  -Dlogging.level.com.zaxxer.hikari=ERROR
```

### Telegram Service
```bash
set -a; [ -f .env ] && . ./.env; set +a
./mvnw spring-boot:run -pl telegram-service -B -q \
  -Dspring.profiles.active=quiet \
  -Dspring.main.banner-mode=off \
  -Dspring.output.ansi.enabled=never \
  -Dspring.devtools.add-properties=false \
  -Dlogging.level.root=ERROR \
  -Dlogging.level.org.springframework=ERROR \
  -Dlogging.level.org.apache=ERROR \
  -Dlogging.level.com.netflix=ERROR \
  -Dlogging.level.org.hibernate=ERROR \
  -Dlogging.level.com.zaxxer.hikari=ERROR
```

## Health checks
```bash
# Eureka
echo "Eureka:" && curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8761
# Chatbot
echo "Chatbot:" && curl -s http://localhost:8080/actuator/health
# Telegram
echo "Telegram:" && curl -s http://localhost:8081/actuator/health
```

## Open UI
- Eureka Dashboard: http://localhost:8761

## Logs
If `nohup` fallback was used by the script:
```bash
# Show last lines
tail -n 200 logs/discovery.log
tail -n 200 logs/chatbot.log
tail -n 200 logs/telegram.log

# Follow a log live
tail -f logs/chatbot.log
```

## Processes and ports
```bash
# Running Spring Boot processes
ps aux | grep "mvnw spring-boot:run" | grep -v grep

# Kill all Spring Boot runs (use with care)
pkill -f "mvnw spring-boot:run"

# Verify ports are listening
ss -ltnp | egrep ':8761|:8080|:8081'
```

## Clean/rebuild
```bash
./mvnw clean
./mvnw clean install -DskipTests
```

## Troubleshooting
- Missing DB URL/password: ensure `.env` exists at project root and is loaded, or let `start-services.sh` load it.
- Port already in use: free the port or kill process using `pkill -f "mvnw spring-boot:run"`.
- Too much logging: the script already enforces ERROR; for stricter control, add a per-service `logback-spring.xml` with root level ERROR.
