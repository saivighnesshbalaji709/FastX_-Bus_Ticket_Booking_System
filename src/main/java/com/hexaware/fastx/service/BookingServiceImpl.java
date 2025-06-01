package com.hexaware.fastx.service;

import java.util.List;

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
    public Booking createBooking(Booking booking) {
        logger.info("Creating booking for user ID: {} and route ID: {}", 
                    booking.getUserId().getUserId(), booking.getRouteId().getRouteId());

        User user = userRepo.findById(booking.getUserId().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + booking.getUserId().getUserId()));
        Route route = routeRepo.findById(booking.getRouteId().getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + booking.getRouteId().getRouteId()));

        booking.setUserId(user);
        booking.setRouteId(route);

        Booking savedBooking = repo.save(booking);
        logger.info("Booking created with ID: {}", savedBooking.getBookingId());
        return savedBooking;
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
