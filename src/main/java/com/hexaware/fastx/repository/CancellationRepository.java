package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

    @Query(value = "SELECT * FROM cancellation", nativeQuery = true)
    List<Cancellation> getAllCancellations();
}
