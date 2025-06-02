package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entity.Cancellation;

import jakarta.transaction.Transactional;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {
	 @Modifying
	@Transactional
	@Query(value = "DELETE FROM booking WHERE booking_id = :bookingId", nativeQuery = true)
    void deleteBookingByBookingId(int bookingId);
	 @Query(value="SELECT * FROM Cancellation_table",nativeQuery = true)
    List<Cancellation> getAllCancellations();
}


