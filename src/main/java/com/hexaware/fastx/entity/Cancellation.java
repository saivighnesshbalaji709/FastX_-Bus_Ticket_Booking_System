package com.hexaware.fastx.entity;

import java.time.LocalDateTime;

public class Cancellation {
	
	private int cancellationId;
	private int bookingId;
	private LocalDateTime cancellationDateTime;
	private double refundAmount;
	
	public Cancellation(int cancellationId, int bookingId, LocalDateTime cancellationDateTime, double refundAmount) {
		super();
		this.cancellationId = cancellationId;
		this.bookingId = bookingId;
		this.cancellationDateTime = cancellationDateTime;
		this.refundAmount = refundAmount;
	}
	public Cancellation() {
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
