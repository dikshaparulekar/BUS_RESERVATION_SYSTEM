# Task 1 – Problem Definition and Scope

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Problem Statement

The Bus Seat Reservation System is designed to provide an online platform for users to reserve bus seats easily.

In a manual reservation process, users may not have a clear view of available seats and may face difficulties while making, cancelling, or tracking a booking.

The proposed system will provide a centralized application where users can register, view available seats, request a booking, receive booking confirmation, cancel a booking, and track their booking status.

The system will also be developed using a DevOps approach with Git, Jenkins, Selenium, Docker, and Ansible/Puppet for automated development, testing, deployment, and configuration management.

---

## 2. Target Users

### 2.1 Passenger/User
The passenger uses the system to:
- Register an account
- Log in
- View available seats
- Book a seat
- View booking status
- Cancel a booking

### 2.2 Administrator
The administrator manages:
- Bus and seat information
- User bookings
- Booking status
- System information

---

## 3. Existing Pain Points

The existing/manual reservation process may have the following problems:

1. Users may not know the current seat availability.
2. Manual booking can take more time.
3. Booking information may not be maintained in one centralized system.
4. Users may have difficulty tracking booking status.
5. Cancellation may require manual communication.
6. Manual processes can result in incorrect or duplicate booking information.

---

## 4. Stakeholders

| Stakeholder | Responsibility / Interest |
|---|---|
| Passenger/User | Uses the system to reserve and manage seats |
| Administrator | Manages buses, seats and bookings |
| Development Team | Develops and maintains the application |
| Testing Team | Tests application functionality and identifies defects |
| DevOps Team | Handles CI/CD, containerization and deployment |
| Project Guide/Faculty | Reviews project progress and deliverables |

---

## 5. Project Objectives

The main objectives of the project are:

1. Provide a simple bus seat reservation system.
2. Allow users to register and log in.
3. Display available and booked seats.
4. Allow users to request a seat booking.
5. Provide booking confirmation and status tracking.
6. Allow users to cancel an existing booking.
7. Maintain booking information in a centralized database.
8. Use Git and GitHub for version control and collaboration.
9. Automate application building using Jenkins.
10. Test important user journeys using Selenium.
11. Containerize the application using Docker.
12. Automate environment configuration and deployment using Ansible/Puppet.

---

## 6. Project Constraints

The following constraints apply to the MVP:

- The project will initially be developed and tested in a local environment.
- The system will focus only on essential bus reservation features.
- The number of buses and seats can be limited for demonstration purposes.
- Online payment is outside the initial MVP scope.
- Live GPS/bus tracking is outside the initial MVP scope.
- Integration with external bus booking providers is outside the initial MVP scope.
- Advanced mobile application functionality is outside the initial MVP scope.
- The project must use the selected development and DevOps tools required for the project tasks.

---

## 7. Measurable Success Criteria

The project will be considered successful when the following criteria are achieved:

| Area | Success Criteria |
|---|---|
| Registration | A new user can successfully register |
| Login | A registered user can successfully log in |
| Seat Availability | The system displays available and booked seats |
| Booking | A user can successfully book an available seat |
| Duplicate Booking | A booked seat cannot be booked again |
| Cancellation | A user can cancel an existing booking |
| Status Tracking | A user can view the current booking status |
| Build | The application builds successfully through Jenkins |
| Testing | Critical Selenium tests execute successfully |
| Docker | The application runs successfully inside a Docker container |
| Configuration | The target environment can be configured using Ansible/Puppet |
| Deployment | The application can be deployed through the planned DevOps workflow |

---

# 8. MVP Scope

The Minimum Viable Product will contain the following core application features:

### 8.1 User Registration
Users can create an account using basic information.

### 8.2 User Login
Registered users can log into the system.

### 8.3 Seat Availability
Users can view the available and already-booked seats.

### 8.4 Seat Booking
Users can select an available seat and submit a booking request.

### 8.5 Booking Cancellation
Users can cancel an existing booking.

### 8.6 Booking Status
Users can view the current status of their booking.

---

# 9. Out-of-Scope Features

The following features are not part of the initial MVP:

- Online payment gateway
- Live GPS tracking
- Mobile application
- Third-party bus provider integration
- Advanced notification system
- Loyalty/reward system
- Multiple external transport integrations

These features can be considered as future enhancements.

---

# 10. 15-Task Project Scope

The complete project will be executed through the following 15 tasks:

| Task No. | Task |
|---|---|
| 1 | Problem Definition and Scope |
| 2 | Agile Planning and DevOps Workflow |
| 3 | Requirements, Architecture and Technology Setup |
| 4 | Git and GitHub Repository Initialization |
| 5 | Feature Development with Branching |
| 6 | MVP Completion and Git Collaboration |
| 7 | Jenkins Installation and Continuous Integration Job |
| 8 | Pipeline as Code and Server Deployment |
| 9 | Selenium Test Design and Local Execution |
| 10 | Continuous Testing in Jenkins |
| 11 | Docker Image and Container Lifecycle |
| 12 | Jenkins-Docker Continuous Deployment |
| 13 | Configuration Management Script |
| 14 | Automated Provisioning and Reliability Validation |
| 15 | Final End-to-End Release, Documentation and Viva |

---

# 11. Final MVP Workflow

The core application workflow will be:

**Register → Login → View Available Seats → Select Seat → Book Seat → View Booking Status → Cancel Booking**

The complete DevOps workflow will later be:

**Git Commit → GitHub → Jenkins Build → Selenium Testing → Docker Image → Container Deployment → Ansible/Puppet Provisioning**

---

## 12. Task 1 Deliverables

The following deliverables are completed for Task 1:

- Problem Statement
- Target Users
- Existing Pain Points
- Stakeholder List
- Project Objectives
- Project Constraints
- Measurable Success Criteria
- MVP Scope
- Out-of-Scope Features
- Frozen 15-Task Project Scope
- Application and DevOps Workflow
