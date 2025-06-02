package com.hexaware.fastx.service;

import java.util.List;

import com.hexaware.fastx.dto.BookingDTO;

public interface IBookingService {
    List<BookingDTO> getAllBookings();
    BookingDTO getBookingById(int id);
    BookingDTO createBooking(BookingDTO dto);
    BookingDTO updateBooking(int id, BookingDTO dto);
    void deleteBooking(int id);
    List<BookingDTO> getBookingsByUserId(int userId);
}
