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
    public List<SeatDTO> getAllSeats() {
        logger.info("Fetching all seats...");
        return repo.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    public SeatDTO addSeat(SeatDTO seatDTO) {
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

        Seat savedSeat = repo.save(seat);
        return convertToDTO(savedSeat);
    }

    @Override
    public SeatDTO getSeatById(int id) {
        logger.info("Fetching seat with ID: {}", id);
        Seat seat = repo.findById(id)
                .orElseThrow(() -> {
                    logger.error("Seat not found with ID: {}", id);
                    return new SeatNotFoundException("Seat not found with ID: " + id);
                });
        return convertToDTO(seat);
    }

    @Override
    public SeatDTO updateSeat(int id, SeatDTO seatDTO) {
        logger.info("Updating seat with ID: {}", id);
        Seat seat = repo.findById(id)
                .orElseThrow(() -> new SeatNotFoundException("Seat not found with ID: " + id));

        Bus bus = busRepository.findById(seatDTO.getBusId())
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + seatDTO.getBusId()));

        Route route = routeRepo.findById(seatDTO.getRouteId())
                .orElseThrow(() -> new SeatNotFoundException("Route not found with id: " + seatDTO.getRouteId()));

        seat.setBus(bus);
        seat.setRouteId(route);
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setBooked(seatDTO.isBooked());

        Seat updatedSeat = repo.save(seat);
        return convertToDTO(updatedSeat);
    }

    @Override
    public void deleteSeat(int id) {
        logger.info("Deleting seat with ID: {}", id);
        Seat seat = repo.findById(id)
                .orElseThrow(() -> new SeatNotFoundException("Seat not found with ID: " + id));
        repo.delete(seat);
    }

    @Override
    public List<SeatDTO> getSeatsByBusId(int busId) {
        logger.info("Fetching seats for bus ID {}", busId);
        return repo.findByBus_BusId(busId).stream()
                   .map(this::convertToDTO)
                   .toList();
    }

    // Helper method to convert entity to DTO
    private SeatDTO convertToDTO(Seat seat) {
        SeatDTO dto = new SeatDTO();
        dto.setSeatId(seat.getSeatId());
        dto.setBusId(seat.getBus().getBusId());
        dto.setRouteId(seat.getRouteId().getRouteId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setBooked(seat.isBooked());
        return dto;
    }
}
