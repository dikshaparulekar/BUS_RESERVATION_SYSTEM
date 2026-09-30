package com.busreservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private int totalSeats;

    public Bus() {}

    public Bus(String busNumber, String source, String destination, int totalSeats) {
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
}
