# Task 4 – Git and GitHub Repository Initialization

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Overview & Setup

This task initializes Git local tracking, sets up branch naming standards, establishes issue templates, and connects the remote repository at:
`https://github.com/dikshaparulekar/BUS_RESERVATION_SYSTEM.git`

---

## 2. Repository Configuration

* **Primary Branch**: `main`
* **Development Branch**: `develop`
* **Feature Branch Convention**: `feature/<feature-name>` (e.g. `feature/registration`, `feature/booking`)
* **Hotfix Branch Convention**: `hotfix/<fix-name>`

---

## 3. Git Deliverables Completed

1. `git init` executed in project root.
2. Set default branch to `main`.
3. Created `.gitignore` excluding `target/`, `.idea/`, `.vscode/`, `.settings/`, `*.class`, and temporary logs.
4. Created `README.md` with application documentation & build instructions.
5. Added GitHub issue templates (`.github/ISSUE_TEMPLATE/bug_report.yml`, `feature_request.yml`).
6. Configured remote origin to `https://github.com/dikshaparulekar/BUS_RESERVATION_SYSTEM.git`.

---

## 4. Conventional Commits Standard

To maintain clean and machine-parseable changelogs, commits adhere to the Conventional Commits specification:

| Prefix | Description | Example |
|---|---|---|
| `feat:` | New user-facing capability or functionality | `feat(auth): add user registration endpoint` |
| `fix:` | Bug fix or patch | `fix(security): resolve session security context` |
| `docs:` | Documentation changes only | `docs(git): define branching model` |
| `test:` | Adding or refactoring automated tests | `test(mvp): add 12 unit and integration tests` |
| `build:` | Changes to build system or dependencies | `build(docker): optimize multi-stage Dockerfile` |
| `ci:` | Continuous Integration / Deployment automation | `ci(jenkins): configure declarative pipeline` |

---

## 5. Branch Lifecycle & Pull Request Strategy

* **Direct commits to `main` restricted** in production settings.
* All development proceeds from `develop` into isolated `feature/*` branches.
* Features must pass automated Maven build and test validation before merging into `develop`.
* Releases are tagged on `main` following semantic versioning (`v1.0.0`).

