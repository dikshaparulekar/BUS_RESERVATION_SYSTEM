# Task 11 & 12 – Docker Image, Container Lifecycle & CD Pipeline

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Dockerfile Architecture

Multi-stage build definition (`Dockerfile`):
* **Build Stage**: `maven:3.9.6-eclipse-temurin-21`
* **Runtime Stage**: `eclipse-temurin:21-jre-alpine`
* **Exposed Port**: 8080

---

## 2. Container Lifecycle Commands

```bash
# Build image
docker build -t bus-reservation:1.0.0 .

# Run container
docker run -d -p 8080:8080 --name bus-app bus-reservation:1.0.0

# Verify running container
docker ps

# View container logs
docker logs bus-app

# Stop and remove container
docker stop bus-app
docker rm bus-app
```
