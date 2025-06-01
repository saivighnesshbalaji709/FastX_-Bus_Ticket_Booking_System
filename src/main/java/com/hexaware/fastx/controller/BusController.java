package com.hexaware.fastx.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastx.entity.Bus;
import com.hexaware.fastx.service.IBusService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/buses")
@Slf4j
@Validated
public class BusController {

    @Autowired
    private IBusService busService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    @PostMapping
    public ResponseEntity<Bus> addBus(@Valid @RequestBody Bus busDTO) {
        Bus createdBus = busService.createBus(busDTO);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    @PutMapping("/{busId}")
    public ResponseEntity<Bus> updateBus(@PathVariable int busId, @Valid @RequestBody Bus busDTO) {
        Bus updatedBus = busService.updateBus(busId, busDTO);
        return ResponseEntity.ok(updatedBus);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/{busId}")
    public ResponseEntity<Bus> getBusById(@PathVariable int busId) {
        Bus bus = busService.getBusById(busId);
        return ResponseEntity.ok(bus);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{busId}")
    public ResponseEntity<String> deleteBus(@PathVariable int busId) {
        busService.deleteBus(busId);
        return ResponseEntity.ok("Bus deleted successfully");
    }
}