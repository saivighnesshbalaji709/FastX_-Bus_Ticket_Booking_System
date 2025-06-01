package com.hexaware.fastx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SeatDTO {

    private int seatId;

    @NotNull(message = "Route ID is required")
    private int routeId;

    @NotBlank(message = "Seat number is required")
    @Size(max = 5, message = "Seat number can't exceed 5 characters")
    private String seatNumber;

    @NotNull(message = "Bus ID is required")
    private int busId;

    private boolean isBooked;

    public SeatDTO() {}

    public SeatDTO(int seatId, int routeId, String seatNumber, int busId, boolean isBooked) {
        this.seatId = seatId;
        this.routeId = routeId;
        this.seatNumber = seatNumber;
        this.busId = busId;
        this.isBooked = isBooked;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return "SeatDTO [seatId=" + seatId + ", routeId=" + routeId + ", seatNumber=" + seatNumber
                + ", busId=" + busId + ", isBooked=" + isBooked + "]";
    }
}
