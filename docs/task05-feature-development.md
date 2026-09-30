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

## 3. Git Evidence

* Branch created: `feature/auth-and-booking`
* Commit hash baseline created
* Pushed to remote: `git push -u origin feature/auth-and-booking`
