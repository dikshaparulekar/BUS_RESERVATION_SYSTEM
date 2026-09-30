package com.busreservation.controller;

import com.busreservation.dto.ApiResponse;
import com.busreservation.dto.BookingRequest;
import com.busreservation.entity.Booking;
import com.busreservation.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@Valid @RequestBody BookingRequest request, Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            Booking booking = bookingService.createBooking(userEmail, request.getSeatId());
            return ResponseEntity.ok(ApiResponse.success("Booking created successfully!", booking));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<Booking>>> getMyBookings(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched successfully", bookingService.getUserBookings(userEmail)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBookingById(@PathVariable Long id, Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            Booking booking = bookingService.getBookingById(id, userEmail);
            return ResponseEntity.ok(ApiResponse.success("Booking fetched successfully", booking));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Booking>> cancelBooking(@PathVariable Long id, Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            Booking booking = bookingService.cancelBooking(id, userEmail);
            return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully!", booking));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
