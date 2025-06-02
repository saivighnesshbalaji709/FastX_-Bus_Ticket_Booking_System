package com.hexaware.restapi.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Cancellation_Table")
public class CancellationDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cancellationId;
	private int bookingId;
	private LocalDateTime cancellationDateTime;
	private double refundAmount;
	
	public CancellationDTO(int cancellationId, int bookingId, LocalDateTime cancellationDateTime, double refundAmount) {
		super();
		this.cancellationId = cancellationId;
		this.bookingId = bookingId;
		this.cancellationDateTime = cancellationDateTime;
		this.refundAmount = refundAmount;
	}
	public CancellationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getCancellationId() {
		return cancellationId;
	}
	public void setCancellationId(int cancellationId) {
		this.cancellationId = cancellationId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDateTime getCancellationDateTime() {
		return cancellationDateTime;
	}
	public void setCancellationDateTime(LocalDateTime cancellationDateTime) {
		this.cancellationDateTime = cancellationDateTime;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	@Override
	public String toString() {
		return "Cancellation [cancellationId=" + cancellationId + ", bookingId=" + bookingId + ", cancellationDateTime="
				+ cancellationDateTime + ", refundAmount=" + refundAmount + "]";
	}


}
