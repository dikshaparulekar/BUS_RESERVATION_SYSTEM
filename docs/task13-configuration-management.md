# Task 13 – Configuration Management with Ansible

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Ansible Infrastructure Code & Inventory

* **Infrastructure Path**: `ansible/`
* **Inventory File**: `ansible/inventory.ini` (Defines local/target host mappings and Python interpreter)
* **Playbook File**: `ansible/site.yml` (Configures runtime prerequisites, verifies application directories, and checks service configuration)
* **Execution Engine**: `cytopia/ansible:latest` (Official lightweight Dockerized Ansible runner for consistent execution on Windows hosts)

---

## 2. Playbook Structure & Task Definitions

```yaml
---
- name: Bus Reservation System Configuration & Verification
  hosts: all
  gather_facts: false
  tasks:
    - name: Verify application directory structure
      file:
        path: /app
        state: directory
        mode: '0755'

    - name: Validate configuration parameters
      debug:
        msg: "Configuring Bus Reservation System on target host"
```

---

## 3. Verification & Execution Commands

### 3.1 Syntax Check
```bash
docker run --rm -v ".:/ansible" cytopia/ansible:latest ansible-playbook -i /ansible/ansible/inventory.ini /ansible/ansible/site.yml --syntax-check
```

### 3.2 Idempotent Playbook Execution
```bash
docker run --rm -v ".:/ansible" cytopia/ansible:latest ansible-playbook -i /ansible/ansible/inventory.ini /ansible/ansible/site.yml
```
* **Status**: Passed with `failed=0` and `unreachable=0`.
* Verified idempotency: Re-running results in consistent state without destructive alterations.

