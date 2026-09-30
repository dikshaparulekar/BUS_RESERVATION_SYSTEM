package com.busreservation.service;

import com.busreservation.entity.Booking;
import com.busreservation.entity.Seat;
import com.busreservation.entity.User;
import com.busreservation.repository.BookingRepository;
import com.busreservation.repository.SeatRepository;
import com.busreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public synchronized Booking createBooking(String userEmail, Long seatId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if ("BOOKED".equalsIgnoreCase(seat.getStatus())) {
            throw new RuntimeException("Seat " + seat.getSeatNumber() + " is already booked!");
        }

        seat.setStatus("BOOKED");
        seatRepository.save(seat);

        Booking booking = new Booking(user, seat, LocalDateTime.now(), "CONFIRMED");
        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUserId(user.getId());
    }

    public Booking getBookingById(Long bookingId, String userEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (!booking.getUser().getEmail().equals(userEmail) && !"ROLE_ADMIN".equals(booking.getUser().getRole())) {
            throw new RuntimeException("Unauthorized to view this booking");
        }
        return booking;
    }

    @Transactional
    public Booking cancelBooking(Long bookingId, String userEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized to cancel this booking");
        }

        if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Booking is already cancelled");
        }

        booking.setStatus("CANCELLED");

        Seat seat = booking.getSeat();
        seat.setStatus("AVAILABLE");
        seatRepository.save(seat);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
