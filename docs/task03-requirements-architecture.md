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

## 5. Non-Functional Requirements (NFRs)

* **NFR-01 Security**: Passwords hashed with BCrypt (strength 10). Session-based authentication via Spring Security with CSRF protection disabled for REST endpoints and frame options disabled for H2 console.
* **NFR-02 Reliability & Concurrency**: Duplicate seat reservations prevented through transaction management and status verification.
* **NFR-03 Portability**: Containerized using multi-stage/alpine OpenJDK runtime executable on any Docker-compliant host.
* **NFR-04 Performance**: In-memory and indexed database queries with sub-100ms response time for seat queries under nominal load.
* **NFR-05 Maintainability**: Layered Spring Boot architecture (Controller -> Service -> Repository -> Entity) separating business rules from data persistence.

---

## 6. Architectural Layers & Data Flow

```
[ Web Browser Client (HTML5/CSS3/Vanilla JS) ]
                      │
              HTTP / REST (Port 8085)
                      ▼
[ Presentation Layer: Spring MVC REST Controllers ]
   ├── AuthController (/api/auth)
   ├── BusController (/api/buses)
   ├── BookingController (/api/bookings)
   └── AdminController (/api/admin)
                      │
[ Business Logic Layer: Spring Services ]
   ├── UserService
   ├── BusService
   └── BookingService
                      │
[ Data Access Layer: Spring Data JPA Repositories ]
   ├── UserRepository
   ├── BusRepository
   ├── SeatRepository
   └── BookingRepository
                      │
[ Persistence Layer: H2 In-Memory / File Database ]
```

---

## 7. Technology Stack Selection & Justification

* **Backend**: Java 21, Spring Boot 3.x — selected for long-term support, built-in dependency injection, and native container support.
* **Frontend**: Responsive Single-Page Application in HTML5, CSS3, JavaScript (Fetch API) — lightweight, no Node runtime needed for client serving.
* **Persistence**: Spring Data JPA + H2 In-Memory Database for rapid CI/CD automated test cycles without external DB provisioning bottlenecks.
* **Build Tool**: Apache Maven 3.9+ — standard declarative build and testing lifecycle.
* **DevOps Infrastructure**: Git, GitHub, Jenkins LTS, Selenium WebDriver, Docker, and Ansible.

