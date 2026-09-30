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
    public void testUserRegistrationAndLogin() {
        RegisterRequest reg = new RegisterRequest("Test User", "testuser@example.com", "password123");
        User registered = userService.registerUser(reg);
        assertNotNull(registered.getId());
        assertEquals("Test User", registered.getName());

        LoginRequest login = new LoginRequest("testuser@example.com", "password123");
        User authenticated = userService.authenticateUser(login);
        assertNotNull(authenticated);
        assertEquals("testuser@example.com", authenticated.getEmail());
    }

    @Test
    public void testDuplicateUserRegistrationPrevented() {
        RegisterRequest reg = new RegisterRequest("Test User", "dupuser@example.com", "password123");
        userService.registerUser(reg);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(reg);
        });
        assertTrue(exception.getMessage().contains("already in use"));
    }

    @Test
    public void testBusAndSeatAvailability() {
        List<Bus> buses = busService.getAllBuses();
        assertFalse(buses.isEmpty());

        Long busId = buses.get(0).getId();
        List<Seat> seats = busService.getSeatsForBus(busId);
        assertFalse(seats.isEmpty());
        assertEquals("AVAILABLE", seats.get(0).getStatus());
    }

    @Test
    public void testBookingAndDuplicateBookingPrevention() {
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

        // Duplicate booking prevention test
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking("bookuser@example.com", seatId);
        });
        assertTrue(exception.getMessage().contains("already booked"));
    }

    @Test
    public void testBookingCancellation() {
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
}
