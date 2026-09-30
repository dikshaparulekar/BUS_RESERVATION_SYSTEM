# Bus Seat Reservation System (Dockerized)

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-green)
![Docker](https://img.shields.io/badge/Docker-29.1.3-blue)

An academic DevOps project demonstrating a complete end-to-end Bus Seat Reservation System with automated CI/CD pipeline, Selenium testing, Docker containerization, and Ansible configuration management.

---

## 🚌 Features & Core MVP Workflow

1. **User Registration & Login**: Authentication using Spring Security and BCrypt password hashing.
2. **Bus & Seat Availability**: View available buses and real-time seat matrix (`AVAILABLE` vs `BOOKED`).
3. **Seat Selection & Booking**: Select available seats and place reservation requests.
4. **Duplicate Booking Prevention**: Thread-safe transactional seat reservation preventing double bookings.
5. **Booking Status & History**: View active and historical booking status.
6. **Booking Cancellation**: Cancel active bookings and instantly release seats back to the pool.

---

## 🛠️ Technology Stack

* **Backend**: Java 21, Spring Boot 3.2.4, Spring Data JPA, Spring Security, BCrypt
* **Frontend**: Vanilla HTML5, CSS3, JavaScript (Fetch API)
* **Build Tool**: Apache Maven 3.9+
* **Database**: H2 (In-memory for dev/testing) / MySQL (Docker deployment)
* **CI/CD**: Jenkins, Jenkinsfile
* **UI Testing**: Selenium WebDriver
* **Containerization**: Docker
* **Configuration Management**: Ansible

---

## 🚀 Quick Start (Local Setup)

### Prerequisites
* Java JDK 21
* Maven 3.9+

### Build & Run
```bash
# Build application
mvn clean package -s D:\maven-settings.xml

# Run application
java -jar target/bus-reservation-system-1.0.0.jar
```

Access the application at: `http://localhost:8080`
