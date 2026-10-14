# Task 12 – Jenkins-Docker Continuous Deployment

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview & CD Pipeline Automation

Task 12 automates continuous delivery by triggering an automated Docker image build and deployment sequence immediately following successful test completion in Jenkins.

* **Target Agent**: `windows-docker-agent` (Windows Jenkins Agent with direct Docker daemon access)
* **Trigger Condition**: All unit and Selenium tests pass with 0 errors
* **Registry & Tagging**: Local Docker daemon tag `bus-reservation-system:${BUILD_NUMBER}` and `latest`

---

## 2. CD Pipeline Stage Definition (`Jenkinsfile`)

```groovy
stage('Docker Build & Package') {
    steps {
        bat "docker build -t bus-reservation-system:${env.BUILD_NUMBER} -t bus-reservation-system:latest ."
    }
}
stage('Continuous Deployment') {
    steps {
        script {
            bat '''
                docker stop bus-reservation-container || true
                docker rm bus-reservation-container || true
                docker run -d -p 8085:8085 --name bus-reservation-container bus-reservation-system:latest
            '''
        }
    }
}
```

---

## 3. Verification & Operational Safeguards

1. **Automated Version Tagging**: Builds are tagged incrementally with Jenkins `${BUILD_NUMBER}` for traceability and rollback capability.
2. **Secret & Credential Isolation**: No credentials hardcoded into pipeline or Dockerfiles.
3. **Container State Preservation**: Zero disruption to running Jenkins infrastructure; isolated deployment port `8085`.
4. **Post-Deployment Verification**: Application verified responsive on `http://localhost:8085/api/buses` post-deployment.
