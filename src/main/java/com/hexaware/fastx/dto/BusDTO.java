package com.hexaware.fastx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BusDTO {

    private int busId;

    @NotBlank(message = "Bus name is required")
    @Size(max = 100, message = "Bus name can't exceed 100 characters")
    private String busName;

    @NotBlank(message = "Bus number is required")
    @Size(max = 50, message = "Bus number can't exceed 50 characters")
    private String busNumber;

    @NotBlank(message = "Bus type is required")
    @Size(max = 50, message = "Bus type can't exceed 50 characters")
    private String busType;

    @Positive(message = "Total seats must be a positive number")
    private int totalSeats;

    @Size(max = 300, message = "Amenities can't exceed 300 characters")
    private String amenities;

    @Positive(message = "Route ID must be positive")
    private int routeId;  // Accept route ID only

    // Getters and setters

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "Bus Details:\n" +
                "-------------\n" +
                "Bus ID       : " + busId + "\n" +
                "Bus Name     : " + busName + "\n" +
                "Bus Number   : " + busNumber + "\n" +
                "Bus Type     : " + busType + "\n" +
                "Total Seats  : " + totalSeats + "\n" +
                "Amenities    : " + amenities + "\n" +
                "Route ID     : " + routeId;
    }
}
