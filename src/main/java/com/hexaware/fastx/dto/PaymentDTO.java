package com.hexaware.fastx.dto;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.hexaware.fastx.entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PaymentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private double amount;

    @NotBlank(message = "Payment method is required")
    @Size(max = 50, message = "Payment method cannot exceed 50 characters")
    private String paymentMethod;

    @NotBlank(message = "Payment status is required")
    @Size(max = 50, message = "Payment status cannot exceed 50 characters")
    private String paymentStatus;

    @NotNull(message = "Payment time is required")
    private LocalDateTime paymentTime;

    public PaymentDTO() {
        super();
    }

    public PaymentDTO(int paymentId, Booking booking, double amount, String paymentMethod, String paymentStatus,
            LocalDateTime paymentTime) {
        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        return "Payment Details:\n" +
                "----------------\n" +
                "Payment ID     : " + paymentId + "\n" +
                "Booking ID     : " + (booking != null ? booking.getBookingId() : "N/A") + "\n" +
                "Amount         : " + String.format("%.2f", amount) + "\n" +
                "Payment Method : " + paymentMethod + "\n" +
                "Payment Status : " + paymentStatus + "\n" +
                "Payment Time   : " + paymentTime;
    }
}
