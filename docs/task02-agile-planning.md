# Task 2 – Agile Planning and DevOps Workflow

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Purpose of Task 2

This task converts the approved project scope from Task 1 into an Agile development plan.

The work will be organized into user stories, acceptance criteria, a product backlog, a 15-task Kanban/Scrum plan, a Definition of Done, and a DevOps lifecycle.

---

# 2. Agile Approach

The project will follow a lightweight **Scrum/Kanban approach**.

- Work will be divided into small tasks.
- Each task will have a clear objective and acceptance criteria.
- Tasks will move through **To Do → In Progress → Review/Testing → Done**.
- Git branches will be used for feature development.
- Jenkins, Selenium, Docker and Ansible/Puppet will be integrated in later stages.
- Completed work will be reviewed before being marked as Done.

---

# 3. User Stories

## US-01 – User Registration

**As a passenger, I want to register an account so that I can use the bus reservation system.**

### Acceptance Criteria
- User can enter required registration details.
- Required fields are validated.
- A user account is created successfully with valid information.
- Duplicate user registration is prevented.
- An appropriate message is displayed after registration.

---

## US-02 – User Login

**As a passenger, I want to log in so that I can access my reservation features.**

### Acceptance Criteria
- User can enter registered credentials.
- Valid credentials allow login.
- Invalid credentials are rejected.
- An appropriate error message is displayed.
- A logged-in user can access reservation features.

---

## US-03 – View Seat Availability

**As a passenger, I want to view available and booked seats so that I can choose an available seat.**

### Acceptance Criteria
- Available seats are displayed.
- Booked seats are clearly identified.
- A booked seat cannot be selected.
- Seat information is loaded correctly from the system.

---

## US-04 – Book a Seat

**As a passenger, I want to book an available seat so that I can reserve a place on the bus.**

### Acceptance Criteria
- User can select an available seat.
- User can submit a booking request.
- The selected seat is marked as booked after successful booking.
- A booking confirmation is displayed.
- The same seat cannot be booked by another user.

---

## US-05 – View Booking Status

**As a passenger, I want to view my booking status so that I know whether my reservation is active or cancelled.**

### Acceptance Criteria
- User can view their bookings.
- Booking status is displayed.
- Booking details are displayed correctly.
- Cancelled bookings are shown with the appropriate status.

---

## US-06 – Cancel Booking

**As a passenger, I want to cancel my booking so that I can release the reserved seat.**

### Acceptance Criteria
- User can select an existing booking.
- User can cancel the booking.
- The booking status changes to cancelled.
- The seat becomes available again.
- The cancelled booking cannot remain active.

---

## US-07 – Manage Reservations

**As an administrator, I want to manage reservation information so that I can monitor the system.**

### Acceptance Criteria
- Administrator can view reservation information.
- Administrator can view booking status.
- Reservation information is displayed correctly.
- Unauthorized users cannot access administrator functionality.

---

# 4. Product Backlog

| ID | Backlog Item | Priority | Related Task |
|---|---|---|---|
| PBI-01 | Project problem definition and scope | High | Task 1 |
| PBI-02 | Create Agile backlog and workflow | High | Task 2 |
| PBI-03 | Define requirements and architecture | High | Task 3 |
| PBI-04 | Select technology stack and setup | High | Task 3 |
| PBI-05 | Initialize GitHub repository | High | Task 4 |
| PBI-06 | Create project skeleton | High | Task 4 |
| PBI-07 | Implement user registration | High | Task 5 |
| PBI-08 | Implement user login | High | Task 5 |
| PBI-09 | Implement seat availability | High | Task 5/6 |
| PBI-10 | Implement seat booking | High | Task 6 |
| PBI-11 | Implement booking status | High | Task 6 |
| PBI-12 | Implement booking cancellation | High | Task 6 |
| PBI-13 | Implement admin reservation management | Medium | Task 6 |
| PBI-14 | Create Jenkins CI job | High | Task 7 |
| PBI-15 | Create Jenkinsfile and deployment pipeline | High | Task 8 |
| PBI-16 | Create Selenium test cases | High | Task 9 |
| PBI-17 | Integrate Selenium tests with Jenkins | High | Task 10 |
| PBI-18 | Create Dockerfile and container | High | Task 11 |
| PBI-19 | Integrate Docker with Jenkins | High | Task 12 |
| PBI-20 | Create Ansible/Puppet configuration | High | Task 13 |
| PBI-21 | Automate provisioning and recovery | High | Task 14 |
| PBI-22 | Complete final release and documentation | High | Task 15 |

---

# 5. 15-Task Kanban/Scrum Plan

| Task | Sprint/Stage | Work Item | Expected Output |
|---|---|---|---|
| 1 | Sprint 1 | Problem Definition and Scope | Approved project scope |
| 2 | Sprint 1 | Agile Planning and DevOps Workflow | Backlog, stories, DoD and workflow |
| 3 | Sprint 1 | Requirements, Architecture and Technology Setup | SRS, architecture and technology setup |
| 4 | Sprint 1 | Git and GitHub Repository Initialization | GitHub repository and project skeleton |
| 5 | Sprint 2 | Feature Development with Branching | First working feature and PR |
| 6 | Sprint 2 | MVP Completion and Git Collaboration | Functional MVP and release tag |
| 7 | Sprint 3 | Jenkins Installation and CI | Successful automated build |
| 8 | Sprint 3 | Pipeline as Code and Deployment | Jenkinsfile and deployment |
| 9 | Sprint 4 | Selenium Test Design | Selenium test suite |
| 10 | Sprint 4 | Continuous Testing in Jenkins | Automated test report and quality gate |
| 11 | Sprint 5 | Docker Image and Container Lifecycle | Docker image and running container |
| 12 | Sprint 5 | Jenkins-Docker Continuous Deployment | Automated container deployment |
| 13 | Sprint 6 | Configuration Management | Ansible/Puppet automation |
| 14 | Sprint 6 | Provisioning and Reliability Validation | Provisioned environment and recovery evidence |
| 15 | Sprint 7 | Final Release, Documentation and Viva | Final project and presentation |

---

# 6. Definition of Done

A task will be considered **Done** only when:

- The required functionality or deliverable is completed.
- The acceptance criteria are satisfied.
- The work has been reviewed.
- Required testing has been completed.
- No known critical defect remains.
- Code follows the agreed project structure and naming conventions.
- Changes are committed to Git with a meaningful commit message.
- Required documentation is updated.
- Evidence such as screenshots, logs, reports or links is collected when required.
- The task is moved to the Done column.
