package com.busreservation.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationSystemE2ETest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String BASE_URL = "http://localhost:8080";

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Headless for CI/CD execution
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

    @Test
    @Order(1)
    public void testUserRegistration() {
        driver.get(BASE_URL);

        WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("regName")));
        nameInput.sendKeys("Selenium Tester");

        driver.findElement(By.id("regEmail")).sendKeys("selenium_test@example.com");
        driver.findElement(By.id("regPassword")).sendKeys("password123");
        driver.findElement(By.cssSelector("#registerForm button[type='submit']")).click();

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertBox")));
        assertTrue(alert.getText().contains("successful") || alert.getText().contains("already in use"));
    }

    @Test
    @Order(2)
    public void testUserLogin() {
        driver.get(BASE_URL);

        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginEmail")));
        emailInput.sendKeys("selenium_test@example.com");
        driver.findElement(By.id("loginPassword")).sendKeys("password123");
        driver.findElement(By.cssSelector("#loginForm button[type='submit']")).click();

        WebElement userHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userNameDisplay")));
        assertTrue(userHeader.getText().contains("Selenium Tester"));
    }

    @Test
    @Order(3)
    public void testViewBusesAndSeatSelection() {
        WebElement busCard = wait.until(ExpectedConditions.elementToBeClickable(By.className("bus-card")));
        busCard.click();

        WebElement seatSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("seatSection")));
        assertTrue(seatSection.isDisplayed());

        WebElement availableSeat = wait.until(ExpectedConditions.elementToBeClickable(By.className("AVAILABLE")));
        availableSeat.click();

        WebElement bookBtn = driver.findElement(By.id("bookSeatBtn"));
        assertTrue(bookBtn.isEnabled());
    }
}
