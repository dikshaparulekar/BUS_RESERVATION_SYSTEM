package com.busreservation.controller;

import com.busreservation.dto.ApiResponse;
import com.busreservation.entity.Bus;
import com.busreservation.entity.Seat;
import com.busreservation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Bus>>> getAllBuses() {
        return ResponseEntity.ok(ApiResponse.success("Buses retrieved successfully", busService.getAllBuses()));
    }

    @GetMapping("/{busId}/seats")
    public ResponseEntity<ApiResponse<List<Seat>>> getSeatsForBus(@PathVariable Long busId) {
        return ResponseEntity.ok(ApiResponse.success("Seats retrieved successfully", busService.getSeatsForBus(busId)));
    }
}
