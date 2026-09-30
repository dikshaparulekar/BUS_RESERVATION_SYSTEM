package com.busreservation.dto;

import jakarta.validation.constraints.NotNull;

public class BookingRequest {
    @NotNull(message = "Seat ID is required")
    private Long seatId;

    public BookingRequest() {}

    public BookingRequest(Long seatId) {
        this.seatId = seatId;
    }

    public Long getSeatId() { return seatId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }
}
