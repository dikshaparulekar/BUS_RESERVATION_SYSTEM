package com.busreservation.service;

import com.busreservation.entity.Bus;
import com.busreservation.entity.Seat;
import com.busreservation.repository.BusRepository;
import com.busreservation.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SeatRepository seatRepository;

    @PostConstruct
    public void initSampleData() {
        if (busRepository.count() == 0) {
            Bus bus1 = new Bus("BUS-101", "Mumbai", "Pune", 10);
            Bus bus2 = new Bus("BUS-202", "Bangalore", "Chennai", 10);
            busRepository.save(bus1);
            busRepository.save(bus2);

            createSeatsForBus(bus1);
            createSeatsForBus(bus2);
        }
    }

    private void createSeatsForBus(Bus bus) {
        for (int i = 1; i <= bus.getTotalSeats(); i++) {
            Seat seat = new Seat(bus, "S" + i, "AVAILABLE");
            seatRepository.save(seat);
        }
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public List<Seat> getSeatsForBus(Long busId) {
        return seatRepository.findByBusId(busId);
    }
}
