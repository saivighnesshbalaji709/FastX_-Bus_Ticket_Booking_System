package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.dto.BookingDTO;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM booking", nativeQuery = true)
    List<BookingDTO> getAllBookings();
    List<Booking> findByUserId(User userId);
	List<Booking> findByRouteId(Route route);
}
