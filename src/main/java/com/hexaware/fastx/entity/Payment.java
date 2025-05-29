package com.hexaware.fastx.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {  // Product Entity class 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	private int bookingId;
	private double amount;
	private String paymentMethod;
	private String paymentStatus; 
	private LocalDateTime paymentTime;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(int paymentId, int bookingId, double amount, String paymentMethod, String paymentStatus,
			LocalDateTime paymentTime) {
		super();
		this.paymentId = paymentId;
		this.bookingId = bookingId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.paymentTime = paymentTime;
	}
	
	/**
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the paymentTime
	 */
	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}
	/**
	 * @param paymentTime the paymentTime to set
	 */
	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Override
	public String toString() {
		return "payment [paymentId=" + paymentId + ", bookingId=" + bookingId + ", amount=" + amount
				+ ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + ", paymentTime="
				+ paymentTime + "]";
	}
}
