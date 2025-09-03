# ❌ Removed docker related code ❌


## 🐳 Docker Setup for Microservices

This guide explains how to run all microservices using Docker and Docker Compose.

## 📋 Prerequisites

1. **Docker**: Install Docker Desktop or Docker Engine
2. **Docker Compose**: Usually comes with Docker Desktop
3. **Environment Variables**: Update `.env` file with your actual values

## 🚀 Quick Start

### Option 1: Using the startup script (Recommended)
```bash
./start-docker.sh
```

### Option 2: Manual Docker Compose commands
```bash
# Build and start all services
docker-compose up --build -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

## 🔧 Configuration

### Environment Variables
Update the `.env` file with your actual values:

```bash
# Database Configuration
DATABASE_URL=your_database_url_here
DATABASE_PASSWORD=your_password_here

# Gemini API Configuration
GEMINI_API_KEY=your_gemini_api_key_here
```

### Service Ports
- **Discovery Service (Eureka)**: http://localhost:8761
- **Chatbot Service**: http://localhost:8080  
- **Telegram Service**: http://localhost:8081

## 📊 Service Management

### Start Services
```bash
./start-docker.sh
```

### Stop Services
```bash
./stop-docker.sh
# or
docker-compose down
```

### View Logs
```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f discovery-service
docker-compose logs -f chatbot-service
docker-compose logs -f telegram-service
```

### Restart a Single Service
```bash
docker-compose restart chatbot-service
```

### Rebuild a Service
```bash
docker-compose up --build -d chatbot-service
```

## 🔍 Debugging

### Check Service Health
```bash
# Discovery Service
curl http://localhost:8761/actuator/health

# Chatbot Service
curl http://localhost:8080/actuator/health

# Telegram Service
curl http://localhost:8081/actuator/health
```

### Access Container Shell
```bash
docker exec -it discovery-service bash
docker exec -it chatbot-service bash
docker exec -it telegram-service bash
```

### View Container Status
```bash
docker-compose ps
```

## 🏗️ Build Process

Each service follows this Docker build process:
1. Copy Maven wrapper and dependencies
2. Copy source code
3. Build JAR file using Maven
4. Run the Spring Boot application

## 🌐 Network Configuration

All services run in a custom Docker network called `microservices-network` which allows:
- Service-to-service communication using container names
- Eureka discovery using `discovery-service:8761`
- Isolated network environment

## 📁 File Structure

```
Project/
├── docker-compose.yml          # Main orchestration file
├── .env                        # Environment variables
├── start-docker.sh            # Startup script
├── stop-docker.sh             # Stop script
├── discovery-service/
│   ├── Dockerfile
│   └── src/main/resources/
│       └── application-docker.yml
├── chatbot-service/
│   ├── Dockerfile
│   └── src/main/resources/
│       └── application-docker.yml
└── telegram-service/
    ├── Dockerfile
    └── src/main/resources/
        └── application-docker.yml
```

## 🎯 Production Notes

1. **Environment Variables**: Never commit real credentials to Git
2. **Resource Limits**: Add memory and CPU limits to docker-compose.yml for production
3. **Health Checks**: All services include health check endpoints
4. **Logging**: Configure centralized logging for production
5. **Monitoring**: Consider adding Prometheus/Grafana for monitoring

## 🆘 Troubleshooting

### Common Issues

1. **Port Already in Use**
   ```bash
   # Find and kill process using port
   lsof -ti:8080 | xargs kill -9
   ```

2. **Docker Build Fails**
   ```bash
   # Clear Docker cache
   docker system prune -a
   ```

3. **Service Not Registering with Eureka**
   - Check network connectivity between containers
   - Verify eureka.client.service-url.defaultZone configuration

4. **Database Connection Issues**
   - Verify DATABASE_URL and DATABASE_PASSWORD in .env
   - Check if database allows connections from Docker network

### Useful Commands
```bash
# View Docker networks
docker network ls

# Inspect microservices network
docker network inspect project_microservices-network

# Remove all stopped containers
docker container prune

# Remove unused images
docker image prune
```
