# Discovery Service

This is the Eureka Server for service discovery in the microservices architecture.

## Overview

The Discovery Service acts as a service registry where all microservices register themselves and discover other services. It runs on port 8761 by default.

## Features

- Service registration and discovery
- Health monitoring of registered services
- Load balancing support
- Service instance management

## Configuration

The service is configured to:
- Run on port 8761
- Not register itself with Eureka (as it is the Eureka server)
- Not fetch registry from other Eureka servers
- Disable self-preservation mode for development

## Running the Service

```bash
mvn spring-boot:run
```

## Access

Once running, you can access the Eureka Dashboard at:
http://localhost:8761

## API Endpoints

- Dashboard: `http://localhost:8761`
- Service Registry: `http://localhost:8761/eureka/apps`
- Health Check: `http://localhost:8761/health`
