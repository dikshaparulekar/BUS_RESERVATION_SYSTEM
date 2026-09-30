# Task 13 & 14 – Ansible Configuration Management & Automated Provisioning

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Ansible Infrastructure Code

Directory: `ansible/`

* **Inventory**: `ansible/inventory.ini`
* **Playbook**: `ansible/site.yml`

---

## 2. Playbook Execution & Idempotency

Command:
```bash
ansible-playbook -i ansible/inventory.ini ansible/site.yml
```

### Idempotency & Reliability Verification
* Running the playbook repeatedly ensures environment state compliance without unintended side effects.
* Health check task queries `http://localhost:8080/actuator/health` to confirm server readiness.
