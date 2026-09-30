# Viva Preparation Guide: Bus Seat Reservation System

## Q1: What is the architecture of the Bus Seat Reservation System?
**Answer**: It follows a clean 3-tier layered architecture:
* **Frontend**: HTML5, CSS3, JavaScript (Fetch API).
* **Backend**: Java 21 with Spring Boot (Controller, Service, Repository layers) and Spring Security.
* **Database**: Spring Data JPA / Hibernate connected to H2/MySQL.

---

## Q2: How is duplicate booking of the same seat prevented?
**Answer**: In `BookingService.java`, seat booking is executed inside a `@Transactional` synchronized method that inspects the seat status before updating it to `BOOKED`. If another user attempts to book an already `BOOKED` seat, an exception is thrown.

---

## Q3: What is the CI/CD workflow implemented in Jenkins?
**Answer**: Defined in the `Jenkinsfile`, the pipeline performs:
1. `Checkout`: Fetches latest code from GitHub.
2. `Build`: Compiles classes with `mvn clean compile`.
3. `Test`: Runs unit & integration tests (`mvn test`).
4. `Package`: Creates executable JAR.
5. `Selenium Tests`: Runs browser UI tests.
6. `Docker`: Packages and deploys container.

---

## Q4: Why use multi-stage Docker builds?
**Answer**: Multi-stage builds separate the compile environment (which contains heavy JDK & Maven tools) from the lightweight runtime environment (`eclipse-temurin:21-jre-alpine`), reducing image size and enhancing security.

---

## Q5: What is idempotency in Ansible?
**Answer**: Idempotency means executing an Ansible playbook multiple times leaves the target system in the exact same state without producing unintended side-effects or duplicating actions.
