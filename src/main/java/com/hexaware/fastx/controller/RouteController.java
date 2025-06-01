package com.hexaware.fastx.controller;

import com.hexaware.fastx.dto.RouteDTO;
import com.hexaware.fastx.entity.Route;
import com.hexaware.fastx.service.IRouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private IRouteService routeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public Route addRoute(@RequestBody RouteDTO routeDTO) {
        return routeService.addRoute(routeDTO);
    }

    @PutMapping("/{routeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public Route updateRoute(@PathVariable int routeId, @RequestBody Route routeDTO) {
        return routeService.updateRoute(routeId, routeDTO);
    }

    @GetMapping("/{routeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public Route getRouteById(@PathVariable int routeId) {
        return routeService.getRouteById(routeId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @DeleteMapping("/{routeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
    public void deleteRoute(@PathVariable int routeId) {
        routeService.deleteRoute(routeId);
    }

    //Search routes by origin
    @GetMapping("/origin/{origin}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public List<Route> getRoutesByOrigin(@PathVariable String origin) {
        return routeService.findByOrigin(origin);
    }

    //Search routes by destination
    @GetMapping("/destination/{destination}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('USER')")
    public List<Route> getRoutesByDestination(@PathVariable String destination) {
        return routeService.findByDestination(destination);
    }

}