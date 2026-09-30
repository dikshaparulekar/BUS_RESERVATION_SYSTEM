# Task 7 & 8 – Jenkins Installation, CI Job & Pipeline as Code

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview

This document specifies the Jenkins Continuous Integration job configuration and Pipeline as Code (`Jenkinsfile`) for the Bus Seat Reservation System.

---

## 2. Pipeline Stages (`Jenkinsfile`)

1. **Checkout**: Checks out source code from GitHub repository (`https://github.com/dikshaparulekar/BUS_RESERVATION_SYSTEM.git`).
2. **Build**: Executes `mvn -s D:\maven-settings.xml clean compile`.
3. **Test**: Runs automated unit and integration tests via `mvn test`.
4. **Package**: Builds the executable Spring Boot JAR (`target/bus-reservation-system-1.0.0.jar`).
5. **Archive**: Archives build artifacts for downstream deployment.

---

## 3. Execution & Setup Instructions

To execute this pipeline in a local Jenkins instance:
1. Open Jenkins at `http://localhost:8080`.
2. Create a new **Pipeline** job named `bus-reservation-pipeline`.
3. Under **Pipeline Definition**, select **Pipeline script from SCM**.
4. Set SCM to **Git** and Repository URL to `https://github.com/dikshaparulekar/BUS_RESERVATION_SYSTEM.git`.
5. Set Script Path to `Jenkinsfile`.
6. Click **Build Now**.
