# Task 9 & 10 – Selenium Test Design and Continuous Testing in Jenkins

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Selenium Test Suite Structure

Located in directory: `selenium-tests/`

* **Test Framework**: JUnit 5 + Selenium WebDriver (Headless Chrome)
* **Target Journeys Tested**:
  1. User Registration (`testUserRegistration`)
  2. User Login & Authentication (`testUserLogin`)
  3. View Bus Availability & Select Seat (`testViewBusesAndSeatSelection`)

---

## 2. Jenkins Integration

Selenium suite is configured to execute post-packaging in Jenkins pipeline:
```groovy
stage('Selenium Tests') {
    steps {
        bat "mvn -s D:\\maven-settings.xml test -f selenium-tests/pom.xml"
    }
}
```
