# Task 6 – MVP Completion and Git Collaboration

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. MVP Baseline

The complete core MVP application baseline is established:
1. User Registration (`POST /api/auth/register`)
2. User Login (`POST /api/auth/login`)
3. Bus & Seat Availability (`GET /api/buses`, `GET /api/buses/{id}/seats`)
4. Seat Selection & Reservation (`POST /api/bookings`)
5. Booking Status (`GET /api/bookings/my`, `GET /api/bookings/{id}`)
6. Booking Cancellation (`PUT /api/bookings/{id}/cancel`)
7. Admin Overview (`GET /api/admin/bookings`, `GET /api/admin/users`)

---

## 2. Git Collaboration & Release Tag

* Feature branch: `feature/admin-and-cancellation`
* Merged into `develop` and `main`
* Release Tag: `v1.0.0`
