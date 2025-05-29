package com.hexaware.fastx.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Route {  // Product Entity class 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int routeId;
	private int busId;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private double fare;
	
	public Route(int routeId, int busId, String origin, String destination, LocalDateTime departureTime,
			LocalDateTime arrivalTime, double fare) {
		super();
		this.routeId = routeId;
		this.busId = busId;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
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
	 * @return the busId
	 */
	public int getBusId() {
		return busId;
	}

	/**
	 * @param busId the busId to set
	 */
	public void setBusId(int busId) {
		this.busId = busId;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the departureTime
	 */
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the fare
	 */
	public double getFare() {
		return fare;
	}

	/**
	 * @param fare the fare to set
	 */
	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "route [routeId=" + routeId + ", busId=" + busId + ", origin=" + origin + ", destination=" + destination
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", fare=" + fare + "]";
	}

	
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}

}