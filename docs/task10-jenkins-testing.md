# Task 10 – Continuous Testing in Jenkins

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview & Objectives

Task 10 establishes continuous test execution within Jenkins pipelines. Every code integration automatically runs unit, integration, and UI acceptance tests, generating standardized test reports and enforcing quality gates.

---

## 2. Test Execution & Reporting Stages in Jenkins

### 2.1 Unit & Integration Test Automation
* Jenkins executes `mvn test` in the pipeline build workspace.
* Surefire XML test reports are generated under `target/surefire-reports/`.
* Jenkins archives and displays JUnit test trends (`junit '**/target/surefire-reports/*.xml'`).
* **Quality Gate**: Build fails immediately if any test fails (`UNSTABLE` / `FAILURE` threshold = 0).

### 2.2 Selenium Automated UI Acceptance Testing
* Configured in Jenkins pipeline post-package:
```groovy
stage('Selenium UI Tests') {
    steps {
        bat "mvn -s ${env.MAVEN_SETTINGS} test -f selenium-tests/pom.xml"
    }
    post {
        always {
            junit 'selenium-tests/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'selenium-tests/test-results/screenshots/*.png', allowEmptyArchive: true
        }
    }
}
```

---

## 3. Failure Behavior & Quality Gate Enforcement

1. **Test Failure Isolation**: If a test assertion fails, the Surefire plugin records the exact stack trace in the XML report.
2. **Pipeline Short-Circuiting**: Downstream Docker container build and deployment stages are blocked if the test phase fails.
3. **Artifact Archiving**: Failure screenshots captured during headless Chrome execution are uploaded to Jenkins build artifacts for immediate debugging.
4. **Verified Test Pass Rate**: 12/12 unit/integration tests and 7/7 Selenium E2E tests pass cleanly.
