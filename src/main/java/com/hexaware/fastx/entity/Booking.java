package com.hexaware.fastx.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {  // Product Entity class 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	private int userId;
	private int routeId;
	private LocalDateTime bookingTime;
	private int seatsBooked;
	private String seatNumbers; 
	private double totalAmount;
	private String status; 
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(int bookingId, int userId, int routeId, LocalDateTime bookingTime, int seatsBooked,
			String seatNumbers, double totalAmount, String status) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.routeId = routeId;
		this.bookingTime = bookingTime;
		this.seatsBooked = seatsBooked;
		this.seatNumbers = seatNumbers;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	
	/**
	 * @return the bookingId
	 */
	public int getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * @return the bookingTime
	 */
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	/**
	 * @param bookingTime the bookingTime to set
	 */
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	/**
	 * @return the seatsBooked
	 */
	public int getSeatsBooked() {
		return seatsBooked;
	}
	/**
	 * @param seatsBooked the seatsBooked to set
	 */
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	/**
	 * @return the seatNumbers
	 */
	public String getSeatNumbers() {
		return seatNumbers;
	}
	/**
	 * @param seatNumbers the seatNumbers to set
	 */
	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "booking [bookingId=" + bookingId + ", userId=" + userId + ", routeId=" + routeId + ", bookingTime="
				+ bookingTime + ", seatsBooked=" + seatsBooked + ", seatNumbers=" + seatNumbers + ", totalAmount="
				+ totalAmount + ", status=" + status + "]";
	}
	


}