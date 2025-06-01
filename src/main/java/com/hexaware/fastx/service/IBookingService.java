package com.hexaware.fastx.service;

import java.util.List;
import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.User;

public interface IBookingService {

    List<BookingDTO> getAllBookings();
    Booking getBookingById(int id);
    List<Booking> getBookingsByUserId(User userId);
    Booking updateBooking(int id, Booking updatedBooking);
    void deleteBooking(int id);
	Booking createBooking(BookingDTO dto);
}
