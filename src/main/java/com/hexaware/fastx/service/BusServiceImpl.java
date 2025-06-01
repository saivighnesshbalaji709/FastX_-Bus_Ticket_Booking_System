package com.hexaware.fastx.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

    @Autowired
    private BusRepository repo;
    
    @Autowired
    private RouteRepository routeRepo;

    @Override
    public Bus addBus(BusDTO dto) {
        Bus bus = new Bus();

        bus.setBusName(dto.getBusName());
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setTotalSeats(dto.getTotalSeats());
        bus.setAmenities(dto.getAmenities());

        Route route = routeRepo.findById(dto.getRouteId())
                .orElseThrow(() -> new RouteNotFoundException("Route not found with ID: " + dto.getRouteId()));

        bus.setRoute(route); 

        return repo.save(bus);
    }


    @Override
    public List<Bus> getAllBuses() {
        logger.info("Fetching all buses from the database");
        return repo.getAllBuses(); 
    }

    @Override
    public Bus getBusById(int id) {
        logger.info("Fetching bus with ID: {}", id);
        return repo.findById(id).orElseThrow(() -> {
            logger.error("Bus not found with ID: {}", id);
            return new BusNotFoundException("No bus found with ID: " + id);
        });
    }

  

    @Override
    public Bus updateBus(int id, Bus updatedBus) {
        logger.info("Updating bus with ID: {}", id);
        Bus existingBus = getBusById(id);

        existingBus.setBusName(updatedBus.getBusName());
        existingBus.setBusNumber(updatedBus.getBusNumber());
        existingBus.setBusType(updatedBus.getBusType());
        existingBus.setTotalSeats(updatedBus.getTotalSeats());
        existingBus.setAmenities(updatedBus.getAmenities());

        Bus savedBus = repo.save(existingBus);
        logger.info("Bus with ID: {} updated successfully", id);
        return savedBus;
    }

    @Override
    public void deleteBus(int id) {
        logger.info("Deleting bus with ID: {}", id);
        Bus b = getBusById(id);
        repo.delete(b);
        logger.info("Bus with ID: {} deleted successfully", id);
    }
}
