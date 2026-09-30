# Task 3 – Requirements, Architecture and Technology Setup

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Purpose of Task 3

This task converts the approved MVP into a technical design that can be implemented in the following tasks.

The system architecture, requirements, technology stack, database structure, API list and local development setup are defined here.

The selected technologies will be used consistently for the implementation, testing, CI/CD, Docker and deployment stages.

---

# 2. Functional Requirements

## FR-01 – User Registration

The system shall allow a new user to create an account.

The system shall:
- Accept required user details.
- Validate required fields.
- Prevent duplicate registration using the same email.
- Store the user details securely.

---

## FR-02 – User Login

The system shall allow registered users to log in.

The system shall:
- Validate email and password.
- Reject invalid credentials.
- Create an authenticated session for a valid user.
- Allow the user to access reservation features after login.

---

## FR-03 – View Bus and Seat Availability

The system shall display bus and seat information.

The system shall:
- Display available seats.
- Display booked seats.
- Prevent users from selecting unavailable seats.

---

## FR-04 – Book a Seat

The system shall allow a logged-in user to book an available seat.

The system shall:
- Accept the selected bus and seat.
- Verify that the seat is still available.
- Create a booking.
- Update the seat status.
- Display booking confirmation.

---

## FR-05 – View Booking Status

The system shall allow a user to view their booking details.

The system shall display:
- Booking ID
- Bus
- Seat number
- Booking date
- Booking status

---

## FR-06 – Cancel Booking

The system shall allow a user to cancel an active booking.

The system shall:
- Verify that the booking belongs to the user.
- Change the booking status to cancelled.
- Make the seat available again.

---

## FR-07 – Administrator Reservation Management

The system shall provide basic administrator access to view reservation information.

The administrator shall be able to:
- View users.
- View buses and seats.
- View bookings.
- View booking status.

---

# 3. Non-Functional Requirements

## Performance

- Normal user requests should receive a response within a reasonable time in the local environment.
- The application should support multiple reservation requests without incorrect seat allocation.

## Security

- Passwords must not be stored as plain text.
- User authentication must be required for reservation operations.
- Users must only be able to manage their own bookings.
- Administrator functionality must require administrator authorization.

## Reliability

- The system should prevent duplicate booking of the same seat.
- Booking and cancellation operations should maintain consistent seat status.

## Maintainability

- The application should follow a layered backend structure.
- Code should be organized into controllers, services and repositories.
- Git should be used for version control.

## Testability

- Core user journeys must be testable using Selenium.
- Backend APIs should be testable independently.
- The application must be suitable for automated Jenkins testing.

## Portability

- The application must run locally using the defined development setup.
- The application must later run inside a Docker container.

---

# 4. Selected Technology Stack

| Layer | Technology | Purpose |
|---|---|---|
| Frontend | HTML5, CSS3, JavaScript | User interface |
| Backend | Java 17 + Spring Boot | REST API and business logic |
| Build Tool | Apache Maven | Build and dependency management |
| Database | MySQL | Store users, buses, seats and bookings |
| ORM | Spring Data JPA / Hibernate | Database interaction |
| Authentication | Spring Security + BCrypt | Authentication and password hashing |
| API | REST | Communication between frontend and backend |
| Version Control | Git | Source control |
| Repository | GitHub | Remote source repository |
| CI/CD | Jenkins | Automated build, test and deployment |
| Testing | Selenium WebDriver | End-to-end UI testing |
| Containerization | Docker | Application container |
| Configuration | Ansible | Environment configuration and provisioning |
| Deployment | Docker container | Application deployment |
| Reverse Proxy / Web Server | Nginx (future deployment option) | Optional external web server/reverse proxy |

### Build Tool Decision

**Apache Maven** is selected instead of Gradle or Ant because it provides a simple and widely used dependency and build management system for the Spring Boot application.

### Configuration Tool Decision

**Ansible** is selected instead of Puppet because the project requires a straightforward configuration and provisioning workflow using YAML playbooks.

---

# 5. System Architecture

The system will use a layered architecture.

```text
                     ┌─────────────────────────┐
                     │       User Browser       │
                     │   HTML/CSS/JavaScript    │
                     └────────────┬────────────┘
                                  │
                                  │ HTTP/REST
                                  ↓
                     ┌─────────────────────────┐
                     │      Spring Boot        │
                     │      REST API Layer     │
                     └────────────┬────────────┘
                                  │
                                  ↓
                     ┌─────────────────────────┐
                     │     Service Layer       │
                     │ Business Logic / Rules  │
                     └────────────┬────────────┘
                                  │
                                  ↓
                     ┌─────────────────────────┐
                     │    Repository Layer     │
                     │    Spring Data JPA      │
                     └────────────┬────────────┘
                                  │
                                  ↓
                     ┌─────────────────────────┐
                     │      MySQL Database     │
                     │ Users / Buses / Seats / │
                     │       Bookings          │
                     └─────────────────────────┘


     Development / DevOps Flow

 Git → GitHub → Jenkins → Maven Build → Selenium Tests
                                      │
                                      ↓
                               Docker Image
                                      │
                                      ↓
                               Docker Container
                                      │
                                      ↓
                                Ansible Setup
```

---

# 6. Application Architecture Layers

## 6.1 Presentation Layer

The frontend will contain:

- Registration page
- Login page
- Bus/seat availability page
- Booking page
- Booking status page
- Admin reservation page

Technologies:

**HTML + CSS + JavaScript**

---

## 6.2 Controller Layer

The controller layer will expose REST endpoints and receive requests from the frontend.

Example controllers:

- `AuthController`
- `BusController`
- `BookingController`
- `AdminController`

---

## 6.3 Service Layer

The service layer will contain the main business rules.

Example services:

- `UserService`
- `AuthService`
- `BusService`
- `BookingService`

---

## 6.4 Repository Layer

The repository layer will communicate with MySQL using Spring Data JPA.

Example repositories:

- `UserRepository`
- `BusRepository`
- `SeatRepository`
- `BookingRepository`

---

## 6.5 Database Layer

MySQL will store:

- User information
- Bus information
- Seat information
- Booking information

---

# 7. Use-Case Summary

```text
                 ┌───────────────────┐
                 │      Passenger    │
                 └─────────┬─────────┘
                           │
             ┌─────────────┼──────────────┐
             ↓             ↓              ↓
       Register/Login   View Seats     View Booking
                           │              Status
                           ↓
                       Book Seat
                           │
                           ↓
                     Cancel Booking


                 ┌───────────────────┐
                 │ Administrator     │
                 └─────────┬─────────┘
                           │
                 ┌─────────┼─────────┐
                 ↓         ↓         ↓
             View Users  View Buses  View Bookings
```

---

# 8. Data Model

The initial database will contain four main entities.

## 8.1 Users

| Field | Type | Description |
|---|---|---|
| id | BIGINT | Primary key |
| name | VARCHAR | User name |
| email | VARCHAR | Unique email |
| password | VARCHAR | BCrypt hashed password |
| role | VARCHAR | USER / ADMIN |

---

## 8.2 Buses

| Field | Type | Description |
|---|---|---|
| id | BIGINT | Primary key |
| bus_number | VARCHAR | Bus identifier |
| source | VARCHAR | Starting location |
| destination | VARCHAR | Destination |
| total_seats | INT | Number of seats |

---

## 8.3 Seats

| Field | Type | Description |
|---|---|---|
| id | BIGINT | Primary key |
| bus_id | BIGINT | Related bus |
| seat_number | VARCHAR | Seat number |
| status | VARCHAR | AVAILABLE / BOOKED |

---

## 8.4 Bookings

| Field | Type | Description |
|---|---|---|
| id | BIGINT | Primary key |
| user_id | BIGINT | User who made booking |
| seat_id | BIGINT | Reserved seat |
| booking_date | DATETIME | Booking time |
| status | VARCHAR | CONFIRMED / CANCELLED |

---

# 9. Database Relationships

```text
Users
  │
  │ 1
  │
  │ N
Bookings
  │
  │ N
  │
  │ 1
Seats
  │
  │ N
  │
  │ 1
Buses
```

### Relationship Explanation

- One user can have multiple bookings.
- Each booking belongs to one user.
- One bus contains multiple seats.
- Each seat belongs to one bus.
- A booking is associated with one seat.

---

# 10. REST API List

The initial API design is:

## Authentication

### Register
```text
POST /api/auth/register
```

### Login
```text
POST /api/auth/login
```

---

## Bus and Seat

### Get all buses
```text
GET /api/buses
```

### Get seats for a bus
```text
GET /api/buses/{busId}/seats
```

---

## Booking

### Create booking
```text
POST /api/bookings
```

### Get user's bookings
```text
GET /api/bookings/my
```

### Get booking by ID
```text
GET /api/bookings/{id}
```

### Cancel booking
```text
PUT /api/bookings/{id}/cancel
```

---

## Administrator

### Get all bookings
```text
GET /api/admin/bookings
```

### Get all users
```text
GET /api/admin/users
```

---

# 11. Project Folder Structure

The Spring Boot project will follow this structure:

```text
bus-seat-reservation-system/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── busreservation/
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── entity/
│   │   │           ├── dto/
│   │   │           ├── security/
│   │   │           └── BusReservationApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   └── js/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│
├── docs/
├── selenium-tests/
├── Dockerfile
├── Jenkinsfile
├── ansible/
├── pom.xml
├── .gitignore
└── README.md
```

---

# 12. Maven Dependencies

The project will use Maven through `pom.xml`.

Required dependency categories:

- Spring Boot Web
- Spring Data JPA
- Spring Security
- MySQL Driver
- Validation
- Spring Boot Test

Selenium dependencies will be added when the testing implementation begins.

---

# 13. Local Development Setup

## Required Software

The development machine should have:

1. Java JDK 17
2. Apache Maven
3. MySQL Server
4. Git
5. GitHub account
6. IDE such as IntelliJ IDEA, Eclipse or VS Code
7. Docker Desktop
8. Jenkins
9. Ansible environment for later provisioning
10. Web browser such as Chrome for Selenium testing

---

# 14. Local Database Setup

Create a MySQL database:

```sql
CREATE DATABASE bus_reservation;
```

The application will connect to this database through Spring Boot configuration.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bus_reservation
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

The actual database password will be kept out of GitHub and should not be committed to the repository.

---

# 15. Local Application Setup

After creating the Spring Boot project:

### Step 1 – Clone the repository

```bash
git clone <repository-url>
```

### Step 2 – Open the project

Open the project in the selected IDE.

### Step 3 – Configure MySQL

Create the `bus_reservation` database and configure the local database credentials.

### Step 4 – Build the project

```bash
mvn clean package
```

### Step 5 – Run the application

```bash
mvn spring-boot:run
```

The application will be available locally on:

```text
http://localhost:8080
```

---

# 16. Deployment Target

The application will use **Docker as the primary deployment target** in the later DevOps stages.

Spring Boot uses an embedded Tomcat server, so the application can run directly inside the Docker container.

The later deployment workflow will be:

```text
GitHub
   ↓
Jenkins
   ↓
Maven Build
   ↓
Selenium Tests
   ↓
Docker Image
   ↓
Docker Container
   ↓
Application
```

Nginx can be introduced later as an optional reverse proxy if required.

---

# 17. Environment Configuration

The project will use separate configuration values for different environments.

Example:

```text
Development
    ↓
Local MySQL
    ↓
localhost:8080

Docker
    ↓
Containerized Application
    ↓
Configured Database

Deployment
    ↓
Docker Container
    ↓
Provisioned Environment
```

Sensitive values such as database passwords should not be hard-coded in source code.

---

# 18. Architecture Decisions

| Decision | Selected Option | Reason |
|---|---|---|
| Backend | Spring Boot | Suitable for REST API development |
| Language | Java 17 | Stable LTS Java version |
| Build | Maven | Simple dependency and build management |
| Database | MySQL | Relational data fits reservation relationships |
| ORM | Spring Data JPA | Simplifies database operations |
| Authentication | Spring Security + BCrypt | Provides authentication and password hashing |
| Frontend | HTML/CSS/JavaScript | Simple frontend for the MVP |
| Version Control | Git | Tracks source changes |
| Repository | GitHub | Remote repository and collaboration |
| CI/CD | Jenkins | Required automation platform |
| UI Testing | Selenium | Required browser automation |
| Container | Docker | Portable deployment |
| Provisioning | Ansible | Simple YAML-based automation |

---

# 19. Traceability from Requirements to Features

| Requirement | Feature | Planned Implementation |
|---|---|---|
| FR-01 | Registration | Auth API + Registration UI |
| FR-02 | Login | Spring Security + Login UI |
| FR-03 | Seat Availability | Bus/Seat API + Seat UI |
| FR-04 | Booking | Booking API + Booking UI |
| FR-05 | Booking Status | Booking Status API + UI |
| FR-06 | Cancellation | Cancellation API + UI |
| FR-07 | Admin Management | Admin APIs + Admin UI |

---

# 20. Task 3 Deliverables

The following deliverables are completed for Task 3:

- SRS Summary
- Functional Requirements
- Non-Functional Requirements
- Selected Technology Stack
- Architecture Diagram
- Application Layer Design
- Use-Case Summary
- Data Model
- Database Relationships
- REST API List
- Project Folder Structure
- Maven Technology Setup
- Local Development Setup
- Database Setup
- Deployment Target
- Environment Configuration Plan
- Architecture Decisions
- Requirement-to-Feature Traceability

---

## 21. Implementation Starting Point

The technical design is now frozen for the MVP.

The implementation sequence will be:

```text
Task 3 – Design & Technology Setup
             ↓
Task 4 – GitHub Repository + Project Skeleton
             ↓
Task 5 – First Feature Implementation
             ↓
Task 6 – Complete MVP
```

**Actual application coding starts in Task 5.**
