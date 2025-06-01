package com.hexaware.fastx.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.fastx.dto.SeatDTO;
import com.hexaware.fastx.entity.Seat;
import com.hexaware.fastx.service.ISeatService;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private ISeatService seatService;

    // Optional: If needed outside of Bus creation (e.g. manual seat addition)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public Seat addSeat(@RequestBody SeatDTO seatDTO) {
        return seatService.addSeat(seatDTO);
    }

    @PutMapping("/{seatId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public Seat updateSeat(@PathVariable int seatId, @RequestBody Seat seatDTO) {
        return seatService.updateSeat(seatId, seatDTO);
    }

    @GetMapping("/{seatId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public Seat getSeatById(@PathVariable int seatId) {
        return seatService.getSeatById(seatId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @DeleteMapping("/{seatId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public void deleteSeat(@PathVariable int seatId) {
        seatService.deleteSeat(seatId);
    }
    @GetMapping("/bus/{busId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public List<Seat> getSeatsByBusId(@PathVariable int busId) {
        return seatService.getSeatsByBusId(busId);
    }
}