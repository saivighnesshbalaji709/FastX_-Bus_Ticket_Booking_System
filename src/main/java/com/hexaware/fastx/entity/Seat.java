package com.hexaware.fastx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Seat {  // Product Entity class 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private int routeId;
	private String seatNumber;
	private boolean isBooked;
	
	public Seat(int seatId, int routeId, String seatNumber, boolean isBooked) {
		super();
		this.seatId = seatId;
		this.routeId = routeId;
		this.seatNumber = seatNumber;
		this.isBooked = isBooked;
	}

	
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return the seatId
	 */
	public int getSeatId() {
		return seatId;
	}
	/**
	 * @param seatId the seatId to set
	 */
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	/**
	 * @return the routeId
	 */
	public int getRouteId() {
		return routeId;
	}
	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	/**
	 * @return the seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}
	/**
	 * @param seatNumber the seatNumber to set
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	/**
	 * @return the isBooked
	 */
	public boolean isBooked() {
		return isBooked;
	}
	/**
	 * @param isBooked the isBooked to set
	 */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	
	@Override
	public String toString() {
		return "seat [seatId=" + seatId + ", routeId=" + routeId + ", seatNumber=" + seatNumber + ", isBooked="
				+ isBooked + "]";
	}


}

