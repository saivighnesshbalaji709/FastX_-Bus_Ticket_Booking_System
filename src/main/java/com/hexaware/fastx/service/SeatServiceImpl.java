package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Seat;
import com.hexaware.fastx.dto.SeatDTO;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.exception.BusNotFoundException;
import com.hexaware.fastx.exception.SeatNotFoundException;
import com.hexaware.fastx.repository.SeatRepository;
import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.repository.RouteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements ISeatService {

    private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

    @Autowired
    private SeatRepository repo;

    @Autowired
    private RouteRepository routeRepo;
    
    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Seat> getAllSeats() {
        logger.info("Fetching all seats...");
        return repo.getAllSeats();
    }
    @Override
    public Seat addSeat(SeatDTO seatDTO) {
        logger.info("Adding new seat for bus ID {}", seatDTO.getBusId());

        Bus bus = busRepository.findById(seatDTO.getBusId())
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + seatDTO.getBusId()));

        Route route = routeRepo.findById(seatDTO.getRouteId())
                .orElseThrow(() -> new SeatNotFoundException("Route not found with id: " + seatDTO.getRouteId()));

        Seat seat = new Seat();
        seat.setBus(bus);
        seat.setRouteId(route);
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setBooked(seatDTO.isBooked());

        return repo.save(seat);
    }
    
    @Override
    public Seat getSeatById(int id) {
        logger.info("Fetching seat with ID: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Seat not found with ID: {}", id);
                    return new SeatNotFoundException("Seat not found with ID: " + id);
                });
    }

    @Override
    public Seat createSeat(Seat seat) {
        logger.info("Creating new seat...");
        Route route = routeRepo.findById(seat.getRouteId().getRouteId())
                .orElseThrow(() -> new SeatNotFoundException("Associated route not found with ID: " + seat.getRouteId().getRouteId()));
        seat.setRouteId(route);
        return repo.save(seat);
    }

    @Override
    public Seat updateSeat(int id, Seat updatedSeat) {
        logger.info("Updating seat with ID: {}", id);
        Seat s = getSeatById(id);

        Route route = routeRepo.findById(updatedSeat.getRouteId().getRouteId())
                .orElseThrow(() -> new SeatNotFoundException("Associated route not found with ID: " + updatedSeat.getRouteId().getRouteId()));

        s.setRouteId(route);
        s.setSeatNumber(updatedSeat.getSeatNumber());
        s.setBooked(updatedSeat.isBooked());

        return repo.save(s);
    }

    @Override
    public void deleteSeat(int id) {
        logger.info("Deleting seat with ID: {}", id);
        Seat s = getSeatById(id);
        repo.delete(s);
    }
	
    @Override
    public List<Seat> getSeatsByBusId(int busId) {
        logger.info("Fetching seats for bus ID {}", busId);
        return repo.findByBus_BusId(busId);
    }
}
