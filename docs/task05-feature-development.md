# Task 5 – Feature Development with Branching

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Feature Workflow & Branch Strategy

This task demonstrates real Git workflow branching by creating feature branch `feature/auth-and-booking` off `develop`.

---

## 2. Feature Implementation Summary

Implemented and verified core user auth and booking flow:
* User registration (`POST /api/auth/register`)
* User authentication (`POST /api/auth/login`)
* Bus seat retrieval (`GET /api/buses/{id}/seats`)
* Seat reservation (`POST /api/bookings`)
* Booking cancellation (`PUT /api/bookings/{id}/cancel`)

---

## 3. Git Evidence & Feature Branch Commits

* **Branch Name**: `feature/auth-and-booking`
* **Parent Branch**: `develop`
* **Key Feature Commit**: `2483ed3` (`Task 5: Implement feature/auth-and-booking flow and documentation`)
* **Remote Tracking**: `origin/feature/auth-and-booking`

---

## 4. Feature Implementation Verification & Code Review Checklist

| Verification Item | Implementation File / Endpoint | Status | Review Notes |
|---|---|---|---|
| User Registration | `UserService.java`, `AuthController.java` (`POST /api/auth/register`) | Verified | Validates duplicate email and encodes password |
| User Authentication | `SecurityConfig.java`, `AuthController.java` (`POST /api/auth/login`) | Verified | Authenticates credentials and sets session context |
| Seat Inspection | `BusService.java`, `BusController.java` (`GET /api/buses/{id}/seats`) | Verified | Returns seat array with `AVAILABLE`/`BOOKED` status |
| Booking Creation | `BookingService.java`, `BookingController.java` (`POST /api/bookings`) | Verified | Atomic booking creation with seat state update |
| Unit Tests | `BusReservationApplicationTests.java` | Verified | All tests passing prior to merge request |

---

## 5. Pull Request & Merging Process

1. Feature branch created: `git checkout -b feature/auth-and-booking develop`
2. Incremental feature commits made with clear commit messages.
3. Local test suite verified via `mvn clean test`.
4. Feature branch pushed to GitHub: `git push -u origin feature/auth-and-booking`.
5. Code reviewed against Definition of Done before merging into `develop`.

