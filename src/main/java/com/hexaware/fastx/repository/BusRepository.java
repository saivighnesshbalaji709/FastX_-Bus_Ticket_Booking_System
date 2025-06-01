package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query(value = "SELECT * FROM bus", nativeQuery = true)
    List<Bus> getAllBuses(); 
}
