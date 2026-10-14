# Task 14 – Automated Provisioning and Reliability Validation

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview & Operational Reliability Goals

Task 14 verifies system provisioning idempotency, operational reliability, automated health monitoring, and rollback strategies.

* **Target Application Endpoint**: `http://localhost:8085`
* **Health Check Endpoints**: `/api/buses`, `/actuator/health`
* **Verification Automation Scripts**: `verify-system.ps1`, `verify-pipeline.bat`

---

## 2. Automated Verification & Health Monitoring

The repository provides automated verification scripts to validate that all services and containers are healthy:

### 2.1 Health Check via PowerShell (`verify-system.ps1`)
```powershell
Write-Host "Verifying Bus Seat Reservation System Health..."
$response = Invoke-RestMethod -Uri "http://localhost:8085/api/buses" -Method Get
if ($response.Count -gt 0) {
    Write-Host "SUCCESS: Application is healthy and returning buses." -ForegroundColor Green
} else {
    Write-Host "ERROR: Application health check failed." -ForegroundColor Red
}
```

### 2.2 Automated Pipeline Verification (`verify-pipeline.bat`)
* Validates container availability and port bindings.
* Executes end-to-end curl / HTTP requests against API endpoints.

---

## 3. Reliability, Idempotency & Rollback Strategies

1. **Idempotent Provisioning**: Re-executing deployment commands or Ansible playbooks does not corrupt active database records or create duplicate resources.
2. **Rollback Strategy**:
   * Versioned Docker images (e.g. `bus-reservation-system:1.0.0` or Jenkins build tags) allow rolling back instantly:
     ```bash
     docker stop bus-reservation-container
     docker rm bus-reservation-container
     docker run -d -p 8085:8085 --name bus-reservation-container bus-reservation-system:<PREVIOUS_BUILD>
     ```
3. **Data Integrity**: In-memory database provides deterministic test isolation, while external persistent volumes can be mounted for production persistence.
