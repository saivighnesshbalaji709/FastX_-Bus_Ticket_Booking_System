package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hexaware.fastx.dto.BusDTO;
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
    public ResponseEntity<BusDTO> addBus(@Valid @RequestBody BusDTO busDTO) {
        BusDTO createdBus = busService.addBus(busDTO);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    @PutMapping("/{busId}")
    public ResponseEntity<BusDTO> updateBus(@PathVariable int busId, @Valid @RequestBody BusDTO busDTO) {
        BusDTO updatedBus = busService.updateBus(busId, busDTO);
        return ResponseEntity.ok(updatedBus);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping("/{busId}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable int busId) {
        BusDTO bus = busService.getBusById(busId);
        return ResponseEntity.ok(bus);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        List<BusDTO> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{busId}")
    public ResponseEntity<String> deleteBus(@PathVariable int busId) {
        busService.deleteBus(busId);
        return ResponseEntity.ok("Bus deleted successfully");
    }
}
