package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query(value = "SELECT * FROM route", nativeQuery = true)
    List<Route> getAllRoutes();
	List<Route> findByOrigin(String origin);
    List<Route> findByDestination(String destination);
}
