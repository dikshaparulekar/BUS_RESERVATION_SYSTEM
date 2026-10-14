# Task 9 – Selenium Test Design and Local Execution

## Project Title
**Dockerized Bus Seat Reservation System**

---

## 1. Selenium Test Suite Structure & Configuration

* **Project Path**: `selenium-tests/`
* **Test Class**: `ReservationSystemE2ETest.java`
* **Framework**: JUnit 5 + Selenium WebDriver 4.18.2 + WebDriverManager 5.7.0
* **Execution Mode**: Google Chrome Headless (`--headless=new`, `--remote-allow-origins=*`, `--no-sandbox`)
* **Target Application URL**: `http://localhost:8085`
* **Artifacts Directory**: `selenium-tests/test-results/screenshots/`

---

## 2. Comprehensive 7 E2E UI Test Cases & Assertions

| # | Test Method Name | Order | Journey Validated | Assertion & Screenshot Evidence |
|---|---|---|---|---|
| 1 | `test1_VerifyPageTitle` | @Order(1) | Homepage and Header Rendering | Asserts title/H1 contains "Bus Seat Reservation System". Screenshot: `test1_page_title.png` |
| 2 | `test2_RegisterNewUser` | @Order(2) | New Passenger Registration | Submits unique timestamped credentials; asserts alert contains "successful". Screenshot: `test2_user_registration.png` |
| 3 | `test3_LoginUser` | @Order(3) | Registered User Authentication | Submits login credentials; asserts `#userNameDisplay` contains user name. Screenshot: `test3_user_login.png` |
| 4 | `test4_VerifyBusAndSeatAccess` | @Order(4) | Dashboard & Seat Grid Navigation | Clicks `.bus-card`; asserts `#seatSection` is displayed. Screenshot: `test4_bus_seat_view.png` |
| 5 | `test5_BookAvailableSeat` | @Order(5) | Seat Reservation | Selects `.AVAILABLE` seat; clicks `#bookSeatBtn`; asserts confirmation alert. Screenshot: `test5_booking_confirmation.png` |
| 6 | `test6_VerifyBookingStatus` | @Order(6) | Booking Status Table Verification | Inspects `#bookingsTableBody`; asserts row exists with "CONFIRMED" status. Screenshot: `test6_booking_status.png` |
| 7 | `test7_CancelBooking` | @Order(7) | Booking Cancellation & Seat Release | Clicks Cancel; accepts alert dialog; asserts cancellation confirmation alert. Screenshot: `test7_booking_cancellation.png` |

---

## 3. Local Test Execution Commands

```bash
cd selenium-tests
mvn test
```
* Generates Surefire XML reports in `selenium-tests/target/surefire-reports/`.
* Captures high-resolution PNG screenshots for each step in `selenium-tests/test-results/screenshots/`.

