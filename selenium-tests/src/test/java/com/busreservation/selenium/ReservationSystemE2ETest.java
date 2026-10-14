package com.busreservation.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationSystemE2ETest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String BASE_URL = "http://localhost:8085";
    private static String uniqueEmail;
    private static String uniqueName;
    private static final String SCREENSHOT_DIR = "test-results/screenshots/";

    @BeforeAll
    public static void setupClass() {
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long timestamp = System.currentTimeMillis();
        uniqueEmail = "selenium_" + timestamp + "@example.com";
        uniqueName = "Selenium User " + timestamp;

        // Configure WebDriverManager to cache on D: drive
        WebDriverManager.chromedriver().cachePath("D:/wdm_cache").setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(SCREENSHOT_DIR + name + ".png");
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    @DisplayName("TEST 1: Verify Page Title")
    public void test1_VerifyPageTitle() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));

        String pageTitle = driver.getTitle();
        String headerText = driver.findElement(By.tagName("h1")).getText();

        takeScreenshot("test1_page_title");
        assertTrue(pageTitle.contains("Bus Seat Reservation System") || headerText.contains("Bus Seat Reservation System"),
                "Page title/header should contain 'Bus Seat Reservation System'");
    }

    @Test
    @Order(2)
    @DisplayName("TEST 2: Register New User")
    public void test2_RegisterNewUser() {
        driver.get(BASE_URL);

        WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("regName")));
        nameInput.sendKeys(uniqueName);
        driver.findElement(By.id("regEmail")).sendKeys(uniqueEmail);
        driver.findElement(By.id("regPassword")).sendKeys("password123");

        driver.findElement(By.cssSelector("#registerForm button[type='submit']")).click();

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertBox")));
        takeScreenshot("test2_user_registration");

        assertTrue(alert.getText().contains("successful"), "Registration alert should indicate success");
    }

    @Test
    @Order(3)
    @DisplayName("TEST 3: Login Registered User")
    public void test3_LoginUser() {
        driver.get(BASE_URL);

        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginEmail")));
        emailInput.sendKeys(uniqueEmail);
        driver.findElement(By.id("loginPassword")).sendKeys("password123");

        driver.findElement(By.cssSelector("#loginForm button[type='submit']")).click();

        WebElement userHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNameDisplay")));
        takeScreenshot("test3_user_login");

        assertTrue(userHeader.getText().contains(uniqueName), "User header should display registered user's name");
    }

    @Test
    @Order(4)
    @DisplayName("TEST 4: Verify Bus & Seat Functionality Access")
    public void test4_VerifyBusAndSeatAccess() {
        WebElement busSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboardSection")));
        assertTrue(busSection.isDisplayed(), "Dashboard section should be visible after login");

        WebElement busCard = wait.until(ExpectedConditions.elementToBeClickable(By.className("bus-card")));
        busCard.click();

        WebElement seatSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("seatSection")));
        takeScreenshot("test4_bus_seat_view");

        assertTrue(seatSection.isDisplayed(), "Seat selection section should be visible after selecting a bus");
    }

    @Test
    @Order(5)
    @DisplayName("TEST 5: Book Available Seat")
    public void test5_BookAvailableSeat() {
        WebElement availableSeat = wait.until(ExpectedConditions.elementToBeClickable(By.className("AVAILABLE")));
        availableSeat.click();

        WebElement bookBtn = driver.findElement(By.id("bookSeatBtn"));
        assertTrue(bookBtn.isEnabled(), "Book button should be enabled after selecting an available seat");

        bookBtn.click();

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertBox")));
        takeScreenshot("test5_booking_confirmation");

        assertTrue(alert.getText().contains("confirmed"), "Booking alert should indicate confirmation");
    }

    @Test
    @Order(6)
    @DisplayName("TEST 6: Verify Booking Status")
    public void test6_VerifyBookingStatus() {
        WebElement tableBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bookingsTableBody")));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        takeScreenshot("test6_booking_status");
        assertFalse(rows.isEmpty(), "Bookings table should contain at least one row");

        String rowText = rows.get(0).getText();
        assertTrue(rowText.contains("CONFIRMED"), "Booking status should display CONFIRMED");
    }

    @Test
    @Order(7)
    @DisplayName("TEST 7: Cancel Booking")
    public void test7_CancelBooking() {
        WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#bookingsTableBody button")));
        cancelBtn.click();

        try {
            Alert confirmAlert = driver.switchTo().alert();
            confirmAlert.accept();
        } catch (NoAlertPresentException ignored) {}

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertBox")));
        takeScreenshot("test7_booking_cancellation");

        assertTrue(alert.getText().contains("cancelled"), "Alert should indicate successful booking cancellation");
    }
}
