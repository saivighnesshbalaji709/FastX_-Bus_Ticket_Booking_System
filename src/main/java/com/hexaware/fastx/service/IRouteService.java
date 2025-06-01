package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.RouteDTO;
import com.hexaware.fastx.entity.Route;
import java.util.List;

public interface IRouteService {
    List<Route> getAllRoutes();
    Route getRouteById(int id);
    Route createRoute(Route route);
    Route updateRoute(int id, Route route);
    void deleteRoute(int id);
    List<Route> findByOrigin(String origin);
    List<Route> findByDestination(String destination);
	Route addRoute(RouteDTO routeDTO);
}
