# Task 7 – Jenkins Installation and Continuous Integration Job

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview & Objectives

Task 7 establishes continuous integration using Jenkins to automatically build and test the Bus Seat Reservation System upon code changes.

* **Jenkins URL**: `http://localhost:8081`
* **Jenkins Controller**: Dockerized LTS container (`jenkins/jenkins:lts-jdk21`)
* **CI Job Name**: `Bus-Reservation-CI` (Freestyle Job)
* **Build Tool**: Apache Maven 3.9+ with JDK 21

---

## 2. Jenkins Freestyle Job Configuration (`Bus-Reservation-CI`)

1. **Source Code Management**:
   * Repository: `https://github.com/dikshaparulekar/BUS_RESERVATION_SYSTEM.git`
   * Branches to build: `*/develop`, `*/main`
2. **Build Triggers**:
   * GitHub hook trigger for GITScm polling
   * Poll SCM schedule: `H/15 * * * *` (regular integration checks)
3. **Build Steps**:
   * Invoke top-level Maven targets: `clean package`
   * Maven POM path: `pom.xml`
4. **Post-build Actions**:
   * **Publish JUnit test result report**: `**/target/surefire-reports/*.xml`
   * **Archive the artifacts**: `target/*.jar`
   * Email/Console notifications on build status change

---

## 3. Verification & Execution Evidence

* **Clean Build**: Maven resolves dependencies, compiles Java 21 sources, and runs application tests.
* **Test Verification**: 12/12 tests executed and reported with 0 failures and 0 errors.
* **Artifact Generation**: `target/bus-reservation-system-1.0.0.jar` created and verified.
* **Continuous Feedback**: Jenkins dashboard displays green build status and test trend charts.

