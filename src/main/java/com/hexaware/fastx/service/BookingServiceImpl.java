package com.hexaware.fastx.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.RouteRepository;
import com.hexaware.fastx.repository.UserRepository;

@Service
public class BookingServiceImpl implements IBookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository repo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RouteRepository routeRepo;

    @Override
    public List<BookingDTO> getAllBookings() {
        logger.info("Fetching all bookings");
        return repo.getAllBookings();
    }

    @Override
    public Booking getBookingById(int id) {
        logger.info("Fetching booking with ID: {}", id);
        return repo.findById(id).orElseThrow(() -> {
            logger.error("Booking not found with ID: {}", id);
            return new BookingNotFoundException("No booking found with ID: " + id);
        });
    }

    @Override
    public Booking createBooking(BookingDTO dto) {
        logger.info("Creating booking for user ID: {} and route ID: {}", dto.getUserId(), dto.getRouteId());

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId()));

        Route route = routeRepo.findById(dto.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + dto.getRouteId()));

        // Fetch all existing bookings for this route
        List<Booking> existingBookings = repo.findByRouteId(route);

        // Collect all already booked seat numbers
        Set<String> bookedSeats = new HashSet<>();
        for (Booking existing : existingBookings) {
            if (existing.getSeatNumbers() != null) {
                String[] seats = existing.getSeatNumbers().split(",");
                for (String seat : seats) {
                    bookedSeats.add(seat.trim());
                }
            }
        }

        // Check if requested seats are already booked
        for (String seat : dto.getSeatNumbers()) {
            if (bookedSeats.contains(seat)) {
                throw new IllegalArgumentException("Seat number " + seat + " is already booked for this route.");
            }
        }

        // If all seats are free, proceed
        Booking booking = new Booking();
        booking.setUserId(user);
        booking.setRouteId(route);
        booking.setSeatsBooked(dto.getSeatsBooked());
        booking.setSeatNumbers(String.join(",", dto.getSeatNumbers()));  // Convert list to comma-separated string
        booking.setTotalAmount(dto.getTotalAmount());
        booking.setStatus(dto.getStatus());
        booking.setBookingTime(dto.getBookingTime());

        Booking saved = repo.save(booking);
        logger.info("Booking created with ID: {}", saved.getBookingId());
        return saved;
    }

    @Override
    public Booking updateBooking(int id, Booking updatedBooking) {
        logger.info("Updating booking with ID: {}", id);

        Booking existingBooking = getBookingById(id);

        User user = userRepo.findById(updatedBooking.getUserId().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + updatedBooking.getUserId().getUserId()));
        Route route = routeRepo.findById(updatedBooking.getRouteId().getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + updatedBooking.getRouteId().getRouteId()));

        existingBooking.setUserId(user);
        existingBooking.setRouteId(route);
        existingBooking.setSeatsBooked(updatedBooking.getSeatsBooked());
        existingBooking.setSeatNumbers(updatedBooking.getSeatNumbers());
        existingBooking.setTotalAmount(updatedBooking.getTotalAmount());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setBookingTime(updatedBooking.getBookingTime());

        Booking savedBooking = repo.save(existingBooking);
        logger.info("Booking with ID: {} updated successfully", id);
        return savedBooking;
    }

    @Override
    public void deleteBooking(int id) {
        logger.info("Deleting booking with ID: {}", id);
        repo.deleteById(id);
        logger.info("Booking with ID: {} deleted", id);
    }

    @Override
    public List<Booking> getBookingsByUserId(User userId) {
        logger.info("Fetching bookings for user ID: {}", userId);
        return repo.findByUserId(userId);
    }
}
