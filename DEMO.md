# Master Project Execution & Demonstration Guide

This guide details the exact steps and commands to run, verify, and demonstrate the complete 15-task DevOps pipeline for the Bus Seat Reservation System.

---

## 1. Local Application Execution (Task 3)

### Step 1: Start Application
```powershell
& 'D:\apache-maven-3.9.16\bin\mvn.cmd' -s D:\maven-settings.xml spring-boot:run
```

### Step 2: Access Application Frontend
Open browser at: `http://localhost:8085`

---

## 2. Automated Verification Script
Run the built-in system verification script:
```powershell
powershell -ExecutionPolicy Bypass -File .\scripts\verify-project.ps1
```

---

## 3. Selenium Automated E2E Testing (Task 9 & 10)
Run the Selenium UI test suite against the running application:
```powershell
& 'D:\apache-maven-3.9.16\bin\mvn.cmd' -s D:\maven-settings.xml test -f selenium-tests/pom.xml
```

---

## 4. Docker Containerization & Deployment (Task 11 & 12)

### Step 1: Start Docker Desktop
Ensure Docker Desktop is running on Windows.

### Step 2: Build & Run Container
```powershell
docker build -t bus-reservation:1.0.0 .
docker run -d -p 8085:8085 --name bus-app bus-reservation:1.0.0
docker ps
```

---

## 5. Jenkins CI/CD Pipeline (Task 7 & 8)
1. Access local Jenkins at `http://localhost:8080`.
2. Create a pipeline job referencing `Jenkinsfile`.
3. Click **Build Now** to execute full build, test, and artifact packaging.
