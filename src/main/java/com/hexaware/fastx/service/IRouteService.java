package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.RouteDTO;
import java.util.List;

public interface IRouteService {
    List<RouteDTO> getAllRoutes();
    RouteDTO getRouteById(int id);
    RouteDTO addRoute(RouteDTO routeDTO);
    RouteDTO updateRoute(int id, RouteDTO routeDTO);
    void deleteRoute(int id);
    List<RouteDTO> findByOrigin(String origin);
    List<RouteDTO> findByDestination(String destination);
}
