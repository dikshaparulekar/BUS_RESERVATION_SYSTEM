# Task 3 – Requirements, Architecture and Technology Setup

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. System Requirements & Architecture

This document defines the functional and non-functional requirements, system architecture, database design, and API endpoints for the Bus Seat Reservation System.

---

## 2. Functional Requirements Summary

* **FR-01 User Registration**: Allow users to register with name, email, and password. Prevent duplicate email registration.
* **FR-02 User Login**: Authenticate user credentials securely via BCrypt and Spring Security.
* **FR-03 Bus & Seat View**: Retrieve available buses and view seats per bus with real-time AVAILABLE/BOOKED status.
* **FR-04 Seat Booking**: Allow authenticated users to book an available seat. Prevent duplicate booking of the same seat.
* **FR-05 Booking Status**: View user-specific active and cancelled bookings.
* **FR-06 Booking Cancellation**: Cancel an existing active booking and release the seat back to AVAILABLE status.
* **FR-07 Admin Management**: View all users, buses, seats, and bookings across the system.

---

## 3. Database Entities & Schema

1. **User**: `id`, `name`, `email`, `password`, `role` (`ROLE_USER`, `ROLE_ADMIN`)
2. **Bus**: `id`, `bus_number`, `source`, `destination`, `total_seats`, `departure_time`
3. **Seat**: `id`, `bus_id`, `seat_number`, `status` (`AVAILABLE`, `BOOKED`)
4. **Booking**: `id`, `user_id`, `seat_id`, `booking_date`, `status` (`CONFIRMED`, `CANCELLED`)

---

## 4. REST API Endpoint Specification

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/api/auth/register` | Register new user | Public |
| POST | `/api/auth/login` | Authenticate user | Public |
| GET | `/api/buses` | Get list of all buses | Public / User |
| GET | `/api/buses/{busId}/seats` | Get seat availability for a bus | Public / User |
| POST | `/api/bookings` | Create seat booking | User |
| GET | `/api/bookings/my` | Get current user's bookings | User |
| GET | `/api/bookings/{id}` | Get specific booking details | User / Admin |
| PUT | `/api/bookings/{id}/cancel` | Cancel an active booking | User / Admin |
| GET | `/api/admin/bookings` | Get all system bookings | Admin |

---

## 5. Technology Stack

* **Backend**: Java 21, Spring Boot 3.x, Spring Data JPA, Spring Security, H2 (dev/test) / MySQL (prod/docker)
* **Frontend**: Vanilla HTML5, CSS3, JavaScript (Fetch API)
* **Build**: Maven 3.9+
* **DevOps**: Git, GitHub, Jenkins, Selenium, Docker, Ansible
