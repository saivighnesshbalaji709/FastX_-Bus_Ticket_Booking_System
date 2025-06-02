package com.hexaware.fastx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Represents a Seat entity.
//A Seat is associated with the bus and Route entities and it has attributes like seatNumber,Bus, etc.

@Entity
public class Seat {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "routeId")
    @NotNull(message = "Route is required")
    private Route routeId;
    
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @NotNull(message = "Bus is required")
    private Bus bus;

	@NotBlank(message = "Seat number is required")
    @Size(max = 5, message = "Seat number can't exceed 5 characters")
    private String seatNumber;

    private boolean isBooked;

    public Seat() {
        super();
    }

    public Seat(int seatId, Route routeId, String seatNumber, boolean isBooked) {
        super();
        this.seatId = seatId;
        this.routeId = routeId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
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
    public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	 @Override
		public String toString() {
			return "Seat [seatId=" + seatId + ", routeId=" + routeId + ", bus=" + bus + ", seatNumber=" + seatNumber
					+ ", isBooked=" + isBooked + "]";
		}


   
}
