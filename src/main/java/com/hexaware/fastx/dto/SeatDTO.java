package com.hexaware.fastx.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.hexaware.fastx.entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class SeatDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "routeId")
    @NotNull(message = "Route is required")
    private Route route;

    @NotBlank(message = "Seat number is required")
    @Size(max = 5, message = "Seat number can't exceed 5 characters")
    private String seatNumber;
    
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @NotNull(message = "Bus is required")
    private int busId;

    private boolean isBooked;

    public SeatDTO() {
        super();
    }

    public SeatDTO(int seatId, Route route, String seatNumber, boolean isBooked) {
        super();
        this.seatId = seatId;
        this.route = route;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
    public int getBusId() {
		return busId;
	}

	public void setBus(int busId) {
		this.busId = busId;
	}
	
	 @Override
		public String toString() {
			return "Seat [seatId=" + seatId + ", routeId=" + route + ", bus=" + busId + ", seatNumber=" + seatNumber
					+ ", isBooked=" + isBooked + "]";
		}

}
