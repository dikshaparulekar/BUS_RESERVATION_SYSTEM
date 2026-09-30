package com.busreservation.controller;

import com.busreservation.dto.ApiResponse;
import com.busreservation.entity.Booking;
import com.busreservation.entity.User;
import com.busreservation.repository.UserRepository;
import com.busreservation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings() {
        return ResponseEntity.ok(ApiResponse.success("All system bookings fetched", bookingService.getAllBookings()));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success("All system users fetched", userRepository.findAll()));
    }
}
