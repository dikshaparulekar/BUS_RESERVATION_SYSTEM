# Task 11 – Docker Image and Container Lifecycle

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Dockerfile Architecture & Optimization

* **Base Runtime Image**: `eclipse-temurin:21-jre-alpine` (Minimal attack surface and lightweight image footprint)
* **Application Binary**: `target/bus-reservation-system-1.0.0.jar`
* **Exposed Port**: `8085` (Configured to avoid conflicts with default Jenkins container on 8080/8081)
* **Entrypoint**: `["java", "-jar", "app.jar"]`

```dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/bus-reservation-system-1.0.0.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 2. Container Lifecycle Commands & Verification

### 2.1 Build Image
```bash
docker build -t bus-reservation-system:latest .
```
* Generates tag `bus-reservation-system:latest`.

### 2.2 Container Execution & Port Mapping
```bash
docker run -d -p 8085:8085 --name bus-reservation-container bus-reservation-system:latest
```
* Binds host port `8085` directly to container port `8085`.

### 2.3 Status Inspection & Logging
```bash
docker ps -a --filter "name=bus-reservation-container"
docker logs bus-reservation-container
```

### 2.4 Container Lifecycle Management
```bash
# Graceful stop
docker stop bus-reservation-container

# Container restart
docker start bus-reservation-container

# Clean container removal
docker rm bus-reservation-container
```

---

## 3. Container Verification Evidence
* Image `bus-reservation-system:latest` verified locally.
* Container `bus-reservation-container` confirmed mapped to `0.0.0.0:8085->8080/8085`.

