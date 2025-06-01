package com.hexaware.fastx.service;

import java.util.List;

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

    @Override
    public List<Route> getAllRoutes() {
        logger.info("Fetching all routes");
        return routeRepo.getAllRoutes();
    }

    @Override
    public Route getRouteById(int id) {
        logger.info("Fetching route by ID: {}", id);
        return routeRepo.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + id));
    }

    @Override
    public Route createRoute(Route route) {
        int busId = route.getBusId().getBusId();
        logger.info("Creating route with bus ID: {}", busId);
        
        // Ensure bus exists
        Bus bus = busRepo.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + busId));
        
        route.setBusId(bus);
        return routeRepo.save(route);
    }

    @Override
    public Route updateRoute(int id, Route updatedRoute) {
        logger.info("Updating route with ID: {}", id);
        Route existingRoute = getRouteById(id);

        int busId = updatedRoute.getBusId().getBusId();
        logger.info("Validating bus ID: {}", busId);

        Bus bus = busRepo.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + busId));

        existingRoute.setBusId(bus);
        existingRoute.setOrigin(updatedRoute.getOrigin());
        existingRoute.setDestination(updatedRoute.getDestination());
        existingRoute.setDepartureTime(updatedRoute.getDepartureTime());
        existingRoute.setArrivalTime(updatedRoute.getArrivalTime());
        existingRoute.setFare(updatedRoute.getFare());

        return routeRepo.save(existingRoute);
    }

    @Override
    public void deleteRoute(int id) {
        logger.info("Deleting route with ID: {}", id);
        Route route = getRouteById(id);
        routeRepo.delete(route);
    }

    @Override
    public List<Route> findByOrigin(String origin) {
        logger.info("Finding routes with origin: {}", origin);
        List<Route> routes = routeRepo.findByOrigin(origin);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException("No routes found from origin: " + origin);
        }
        return routes;
    }

    @Override
    public List<Route> findByDestination(String destination) {
        logger.info("Finding routes with destination: {}", destination);
        List<Route> routes = routeRepo.findByDestination(destination);
        if (routes.isEmpty()) {
            throw new RouteNotFoundException("No routes found to destination: " + destination);
        }
        return routes;
    }
    @Override
    public Route addRoute(RouteDTO routeDTO) {
    	logger.info("Adding new route: {}", routeDTO);
        Route route = new Route();
        Bus bus = busRepo.findById(routeDTO.getBusId())
                .orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + routeDTO.getBusId()));
        route.setBusId(bus);
        route.setOrigin(routeDTO.getOrigin());
        route.setDestination(routeDTO.getDestination());
        route.setDepartureTime(routeDTO.getDepartureTime());
        route.setArrivalTime(routeDTO.getArrivalTime());
        route.setFare(routeDTO.getFare());
        return routeRepo.save(route);
    }
	
}
