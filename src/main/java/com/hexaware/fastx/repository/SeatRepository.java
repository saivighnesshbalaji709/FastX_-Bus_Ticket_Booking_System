package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query(value = "SELECT * FROM seat", nativeQuery = true)
    List<Seat> getAllSeats();

	List<Seat> findByBus_BusId(int busId);
}
