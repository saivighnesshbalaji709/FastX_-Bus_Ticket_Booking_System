package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.service.IBookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO dto) {
        BookingDTO createdBooking = bookingService.createBooking(dto);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable int bookingId, @RequestBody BookingDTO dto) {
        BookingDTO updatedBooking = bookingService.updateBooking(bookingId, dto);
        return ResponseEntity.ok(updatedBooking);
    }

    @GetMapping("/{bookingId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable int bookingId) {
        BookingDTO booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #userId == principal.userId)")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable int userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
