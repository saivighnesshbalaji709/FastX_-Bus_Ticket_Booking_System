package com.hexaware.fastx.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.RouteRepository;
import com.hexaware.fastx.repository.UserRepository;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository repo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RouteRepository routeRepo;

    private BookingDTO mapToDTO(Booking booking) {
        return new BookingDTO(
            booking.getBookingId(),
            booking.getUserId().getUserId(),
            booking.getRouteId().getRouteId(),
            booking.getBookingTime(),
            booking.getSeatsBooked(),
            Arrays.asList(booking.getSeatNumbers().split(",")),
            booking.getTotalAmount(),
            booking.getStatus()
        );
    }

    private Booking convertToEntity(BookingDTO dto) {
        Booking booking = new Booking();

        if (dto.getBookingId() != 0) {
            booking.setBookingId(dto.getBookingId());
        }

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId()));

        Route route = routeRepo.findById(dto.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + dto.getRouteId()));

        booking.setUserId(user);
        booking.setRouteId(route);
        booking.setBookingTime(dto.getBookingTime());
        booking.setSeatsBooked(dto.getSeatsBooked());
        booking.setSeatNumbers(String.join(",", dto.getSeatNumbers()));
        booking.setTotalAmount(dto.getTotalAmount());
        booking.setStatus(dto.getStatus());

        return booking;
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = repo.findAll();
        return bookings.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(int id) {
        Booking booking = repo.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("No booking found with ID: " + id));
        return mapToDTO(booking);
    }

    @Override
    public BookingDTO createBooking(BookingDTO dto) {
        Booking booking = convertToEntity(dto);

        // Check seat availability for the route:
        List<Booking> existingBookings = repo.findByRouteId(booking.getRouteId());

        Set<String> bookedSeats = new HashSet<>();
        for (Booking b : existingBookings) {
            if (b.getSeatNumbers() != null) {
                bookedSeats.addAll(Arrays.asList(b.getSeatNumbers().split(",")));
            }
        }

        for (String seat : dto.getSeatNumbers()) {
            if (bookedSeats.contains(seat)) {
                throw new IllegalArgumentException("Seat number " + seat + " is already booked.");
            }
        }

        Booking saved = repo.save(booking);
        return mapToDTO(saved);
    }

    @Override
    public BookingDTO updateBooking(int id, BookingDTO dto) {
        Booking existingBooking = repo.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("No booking found with ID: " + id));

        Booking updatedBooking = convertToEntity(dto);
        updatedBooking.setBookingId(id);

        // Optional: check seat availability logic here if needed

        Booking saved = repo.save(updatedBooking);
        return mapToDTO(saved);
    }

    @Override
    public void deleteBooking(int id) {
        if (!repo.existsById(id)) {
            throw new BookingNotFoundException("No booking found with ID: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        List<Booking> bookings = repo.findByUserId(user);
        return bookings.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
