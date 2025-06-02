package com.hexaware.fastx.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//Represents a Route entity.
//A Route can be associated with the bus entity and it has attributes such as origin, destination, timings, etc.

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @NotNull(message = "Bus must not be null")
    @JsonBackReference
    private Bus busId;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @Min(value = 0, message = "Fare must be positive or zero")
    private double fare;

    public Route() {
        super();
    }

    public Route(int routeId, Bus busId, String origin, String destination, LocalDateTime departureTime,
                 LocalDateTime arrivalTime, double fare) {
        this.routeId = routeId;
        this.busId = busId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fare = fare;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Bus getBusId() {
        return busId;
    }

    public void setBusId(Bus busId) {
        this.busId = busId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Route [routeId=" + routeId +
               ", busId=" + (busId != null ? busId.getBusId() : "N/A") +
               ", origin=" + origin +
               ", destination=" + destination +
               ", departureTime=" + departureTime +
               ", arrivalTime=" + arrivalTime +
               ", fare=" + fare + "]";
    }
}
