# Task 8 – Pipeline as Code and Server Deployment

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview & Declarative Pipeline Architecture

Task 8 transitions continuous delivery into code via `Jenkinsfile` running on the Windows Jenkins agent (`windows-docker-agent`).

* **Jenkinsfile Type**: Declarative Pipeline
* **Target Node / Agent**: Windows execution node (`agent any` / `windows-docker-agent`)
* **Environment Configuration**: Explicit Maven tool & settings repository path (`D:\maven-settings.xml`)

---

## 2. Pipeline Stages & Execution Flow

```
[ Checkout SCM ]
       │
       ▼
[ Build Stage: mvn clean compile ]
       │
       ▼
[ Test Stage: mvn test ]
       │
       ▼
[ Package Stage: mvn package -DskipTests ]
       │
       ▼
[ Post Actions: archiveArtifacts target/*.jar ]
```

### Stage Breakdown:
1. **Checkout**: Checks out exact commit revision from GitHub repository `https://github.com/dikshaparulekar/BUS_RESERVATION_SYSTEM.git`.
2. **Build**: Compiles Java 21 sources against Spring Boot dependencies using `mvn clean compile`.
3. **Test**: Executes automated JUnit 5 test suite with test report generation.
4. **Package**: Generates production-ready executable Spring Boot jar (`target/bus-reservation-system-1.0.0.jar`).
5. **Post Always**: Archives generated jar artifacts in Jenkins build workspace.

---

## 3. Server Deployment Verification

* Pipeline builds execute on dedicated agent workspace.
* Artifacts published and verified via Jenkins UI build history.
* Build logs confirm zero syntax warnings, clean compilation, and zero test failures.
