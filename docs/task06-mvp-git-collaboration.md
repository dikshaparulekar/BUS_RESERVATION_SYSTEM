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
* Merged into `develop` and `main` with commit `8d0f3f9`
* Release Tag: `v1.0.0`

---

## 3. Automated Application Test Suite Verification (12 Tests Passing)

The application test suite in `BusReservationApplicationTests.java` covers all critical functional user journeys:

| # | Test Method Name | Validated Functionality | Expected & Verified Outcome |
|---|---|---|---|
| 1 | `test1_SuccessfulRegistration` | User registration | Account generated with ID and name |
| 2 | `test2_DuplicateRegistrationPrevented` | Duplicate email prevention | Exception thrown: email already in use |
| 3 | `test3_SuccessfulLogin` | User authentication | User authenticated and session established |
| 4 | `test4_InvalidPasswordLogin` | Bad credentials | Rejection with "Invalid email or password" |
| 5 | `test5_UnknownEmailLogin` | Unregistered user login | Rejection with "Invalid email or password" |
| 6 | `test6_BusRetrieval` | Bus inventory list | Non-empty bus listing returned |
| 7 | `test7_SeatAvailability` | Seat layout for bus | Returns seat list with status |
| 8 | `test8_SuccessfulBooking` | Seat reservation | Booking confirmed, seat status -> BOOKED |
| 9 | `test9_DuplicateSeatBookingPrevention` | Concurrency check | Exception thrown: seat already booked |
| 10 | `test10_BookingStatus` | User booking query | Booking visible in user's reservation list |
| 11 | `test11_SuccessfulCancellation` | Booking cancellation | Booking status -> CANCELLED, seat -> AVAILABLE |
| 12 | `test12_UnauthorizedCancellation` | Security permission check | Exception thrown: unauthorized cancellation prevented |

---

## 4. UI & Session Security Enhancements

* Configured explicit `/api/auth/logout` endpoint in `AuthController.java` with session invalidation and SecurityContext clearing.
* Enforced `HttpSessionSecurityContextRepository` in `SecurityConfig.java` to guarantee persistence across subsequent HTTP REST requests.
* Integrated modern async client-side logout flow in `index.html`.

