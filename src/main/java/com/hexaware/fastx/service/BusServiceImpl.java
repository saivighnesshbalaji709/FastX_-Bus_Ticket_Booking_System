package com.hexaware.fastx.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.BusDTO;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.exception.BusNotFoundException;
import com.hexaware.fastx.exception.RouteNotFoundException;
import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.repository.RouteRepository;

@Service
public class BusServiceImpl implements IBusService {

    @Autowired
    private BusRepository repo;

    @Autowired
    private RouteRepository routeRepo;

    // Convert Entity -> DTO
    private BusDTO convertToDTO(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setBusId(bus.getBusId());
        dto.setBusName(bus.getBusName());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAmenities(bus.getAmenities());
        dto.setRouteId(bus.getRoute() != null ? bus.getRoute().getRouteId() : 0);
        return dto;
    }

    // Convert DTO -> Entity
    private Bus convertToEntity(BusDTO dto) {
        Bus bus = new Bus();
        bus.setBusName(dto.getBusName());
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setTotalSeats(dto.getTotalSeats());
        bus.setAmenities(dto.getAmenities());

        Route route = routeRepo.findById(dto.getRouteId())
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRouteId()));

        bus.setRoute(route);
        return bus;
    }

    @Override
    public BusDTO addBus(BusDTO dto) {
        Bus bus = convertToEntity(dto);
        Bus savedBus = repo.save(bus);
        return convertToDTO(savedBus);
    }

    @Override
    public List<BusDTO> getAllBuses() {
        return repo.getAllBuses()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BusDTO getBusById(int id) {
        Bus bus = repo.findById(id)
                .orElseThrow(() -> new BusNotFoundException("No bus found with ID: " + id));
        return convertToDTO(bus);
    }

    @Override
    public BusDTO updateBus(int id, BusDTO dto) {
        Bus existingBus = repo.findById(id)
                .orElseThrow(() -> new BusNotFoundException("No bus found with ID: " + id));

        existingBus.setBusName(dto.getBusName());
        existingBus.setBusNumber(dto.getBusNumber());
        existingBus.setBusType(dto.getBusType());
        existingBus.setTotalSeats(dto.getTotalSeats());
        existingBus.setAmenities(dto.getAmenities());

        Route route = routeRepo.findById(dto.getRouteId())
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRouteId()));

        existingBus.setRoute(route);

        Bus savedBus = repo.save(existingBus);
        return convertToDTO(savedBus);
    }

    @Override
    public void deleteBus(int id) {
        Bus bus = repo.findById(id)
                .orElseThrow(() -> new BusNotFoundException("No bus found with ID: " + id));
        repo.delete(bus);
    }
}
