package com.hexaware.fastx.entity;

import jakarta.persistence.Entity;

@Entity
public class BookingSeat {
	
	private int bookingSeatId;
	private int bookingId;
	private int seatId;
	
	public BookingSeat(int bookingSeatId, int bookingId, int seatId) {
		super();
		this.bookingSeatId = bookingSeatId;
		this.bookingId = bookingId;
		this.seatId = seatId;
	}
	public BookingSeat() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BookingSeat [bookingSeatId=" + bookingSeatId + ", bookingId=" + bookingId + ", seatId=" + seatId + "]";
	}
	public int getBookingSeatId() {
		return bookingSeatId;
	}
	public void setBookingSeatId(int bookingSeatId) {
		this.bookingSeatId = bookingSeatId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	

}
