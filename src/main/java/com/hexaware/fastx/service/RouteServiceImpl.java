package com.hexaware.fastx.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.RouteDTO;
import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.exception.BusNotFoundException;
import com.hexaware.fastx.exception.RouteNotFoundException;
import com.hexaware.fastx.repository.BusRepository;
import com.hexaware.fastx.repository.RouteRepository;

@Service
public class RouteServiceImpl implements IRouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private BusRepository busRepo;

    // Mapper: Entity -> DTO
    private RouteDTO mapToDTO(Route route) {
        RouteDTO dto = new RouteDTO();
        dto.setRouteId(route.getRouteId());
        dto.setBusId(route.getBusId().getBusId());
        dto.setOrigin(route.getOrigin());
        dto.setDestination(route.getDestination());
        dto.setDepartureTime(route.getDepartureTime());
        dto.setArrivalTime(route.getArrivalTime());
        dto.setFare(route.getFare());
        return dto;
    }

    // Mapper: DTO -> Entity (for create/update)
    private Route mapToEntity(RouteDTO dto) {
        Route route = new Route();
        Bus bus = busRepo.findById(dto.getBusId())
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + dto.getBusId()));
        route.setBusId(bus);
        route.setOrigin(dto.getOrigin());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setFare(dto.getFare());
        return route;
    }

    @Override
    public List<RouteDTO> getAllRoutes() {
        logger.info("Fetching all routes");
        List<Route> routes = routeRepo.getAllRoutes();
        return routes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDTO getRouteById(int id) {
        logger.info("Fetching route by ID: {}", id);
        Route route = routeRepo.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + id));
        return mapToDTO(route);
    }

    @Override
    public RouteDTO addRoute(RouteDTO routeDTO) {
        logger.info("Adding new route: {}", routeDTO);
        Route route = mapToEntity(routeDTO);
        Route savedRoute = routeRepo.save(route);
        return mapToDTO(savedRoute);
    }

    @Override
    public RouteDTO updateRoute(int id, RouteDTO routeDTO) {
        logger.info("Updating route with ID: {}", id);
        Route existingRoute = routeRepo.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + id));

        Bus bus = busRepo.findById(routeDTO.getBusId())
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + routeDTO.getBusId()));

        existingRoute.setBusId(bus);
        existingRoute.setOrigin(routeDTO.getOrigin());
        existingRoute.setDestination(routeDTO.getDestination());
        existingRoute.setDepartureTime(routeDTO.getDepartureTime());
        existingRoute.setArrivalTime(routeDTO.getArrivalTime());
        existingRoute.setFare(routeDTO.getFare());

        Route updatedRoute = routeRepo.save(existingRoute);
        return mapToDTO(updatedRoute);
    }

    @Override
    public void deleteRoute(int id) {
        logger.info("Deleting route with ID: {}", id);
        Route route = routeRepo.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + id));
        routeRepo.delete(route);
    }

    @Override
    public List<RouteDTO> findByOrigin(String origin) {
        logger.info("Finding routes with origin: {}", origin);
        List<Route> routes = routeRepo.findByOrigin(origin);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException("No routes found from origin: " + origin);
        }
        return routes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteDTO> findByDestination(String destination) {
        logger.info("Finding routes with destination: {}", destination);
        List<Route> routes = routeRepo.findByDestination(destination);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException("No routes found to destination: " + destination);
        }
        return routes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
