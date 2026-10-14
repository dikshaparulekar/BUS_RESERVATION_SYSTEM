package com.busreservation;

import com.busreservation.dto.BookingRequest;
import com.busreservation.dto.LoginRequest;
import com.busreservation.dto.RegisterRequest;
import com.busreservation.entity.Booking;
import com.busreservation.entity.Bus;
import com.busreservation.entity.Seat;
import com.busreservation.entity.User;
import com.busreservation.service.BookingService;
import com.busreservation.service.BusService;
import com.busreservation.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BusReservationApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private BusService busService;

    @Autowired
    private BookingService bookingService;

    @BeforeEach
    public void setup() {
        busService.initSampleData();
    }

    @Test
    public void test1_SuccessfulRegistration() {
        RegisterRequest reg = new RegisterRequest("Test User", "testuser@example.com", "password123");
        User registered = userService.registerUser(reg);
        assertNotNull(registered.getId());
        assertEquals("Test User", registered.getName());
    }

    @Test
    public void test2_DuplicateRegistrationPrevented() {
        RegisterRequest reg = new RegisterRequest("Test User", "dupuser@example.com", "password123");
        userService.registerUser(reg);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(reg);
        });
        assertTrue(exception.getMessage().contains("already in use"));
    }

    @Test
    public void test3_SuccessfulLogin() {
        RegisterRequest reg = new RegisterRequest("Login User", "loginuser@example.com", "password123");
        userService.registerUser(reg);

        LoginRequest login = new LoginRequest("loginuser@example.com", "password123");
        User authenticated = userService.authenticateUser(login);
        assertNotNull(authenticated);
        assertEquals("loginuser@example.com", authenticated.getEmail());
    }

    @Test
    public void test4_InvalidPasswordLogin() {
        RegisterRequest reg = new RegisterRequest("Login User", "badpass@example.com", "password123");
        userService.registerUser(reg);

        LoginRequest login = new LoginRequest("badpass@example.com", "wrongpassword");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.authenticateUser(login);
        });
        assertTrue(exception.getMessage().contains("Invalid email or password"));
    }

    @Test
    public void test5_UnknownEmailLogin() {
        LoginRequest login = new LoginRequest("unknown@example.com", "password123");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.authenticateUser(login);
        });
        assertTrue(exception.getMessage().contains("Invalid email or password"));
    }

    @Test
    public void test6_BusRetrieval() {
        List<Bus> buses = busService.getAllBuses();
        assertFalse(buses.isEmpty());
    }

    @Test
    public void test7_SeatAvailability() {
        List<Bus> buses = busService.getAllBuses();
        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        assertFalse(seats.isEmpty());
        assertEquals("AVAILABLE", seats.get(0).getStatus());
    }

    @Test
    public void test8_SuccessfulBooking() {
        RegisterRequest reg = new RegisterRequest("Booking User", "bookuser@example.com", "password123");
        userService.registerUser(reg);

        List<Bus> buses = busService.getAllBuses();
        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        Long seatId = seats.get(0).getId();

        Booking booking = bookingService.createBooking("bookuser@example.com", seatId);
        assertNotNull(booking.getId());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals("BOOKED", booking.getSeat().getStatus());
    }

    @Test
    public void test9_DuplicateSeatBookingPrevention() {
        RegisterRequest reg1 = new RegisterRequest("User 1", "user1@example.com", "password123");
        RegisterRequest reg2 = new RegisterRequest("User 2", "user2@example.com", "password123");
        userService.registerUser(reg1);
        userService.registerUser(reg2);

        List<Bus> buses = busService.getAllBuses();
        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        Long seatId = seats.get(0).getId();

        bookingService.createBooking("user1@example.com", seatId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking("user2@example.com", seatId);
        });
        assertTrue(exception.getMessage().contains("already booked"));
    }

    @Test
    public void test10_BookingStatus() {
        RegisterRequest reg = new RegisterRequest("Status User", "statususer@example.com", "password123");
        userService.registerUser(reg);

        List<Bus> buses = busService.getAllBuses();
        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        Long seatId = seats.get(0).getId();

        Booking created = bookingService.createBooking("statususer@example.com", seatId);
        List<Booking> userBookings = bookingService.getUserBookings("statususer@example.com");

        assertFalse(userBookings.isEmpty());
        assertEquals(created.getId(), userBookings.get(0).getId());
    }

    @Test
    public void test11_SuccessfulCancellation() {
        RegisterRequest reg = new RegisterRequest("Cancel User", "canceluser@example.com", "password123");
        userService.registerUser(reg);

        List<Bus> buses = busService.getAllBuses();
        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        Long seatId = seats.get(0).getId();

        Booking booking = bookingService.createBooking("canceluser@example.com", seatId);
        assertEquals("CONFIRMED", booking.getStatus());

        Booking cancelled = bookingService.cancelBooking(booking.getId(), "canceluser@example.com");
        assertEquals("CANCELLED", cancelled.getStatus());
        assertEquals("AVAILABLE", cancelled.getSeat().getStatus());
    }

    @Test
    public void test12_UnauthorizedCancellation() {
        RegisterRequest reg1 = new RegisterRequest("Owner User", "owner@example.com", "password123");
        RegisterRequest reg2 = new RegisterRequest("Other User", "other@example.com", "password123");
        userService.registerUser(reg1);
        userService.registerUser(reg2);

        List<Bus> buses = busService.getAllBuses();
        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        Long seatId = seats.get(0).getId();

        Booking booking = bookingService.createBooking("owner@example.com", seatId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.cancelBooking(booking.getId(), "other@example.com");
        });
        assertTrue(exception.getMessage().contains("Unauthorized"));
    }
}
