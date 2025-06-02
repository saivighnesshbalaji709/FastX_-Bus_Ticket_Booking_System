/* Date: 02-06-2025
 * Author: Sai Vighnessh
 * 
 * Represents a Payment entity.
 * A payment can be associated with booking, user and has attributes such as paymentMethod, status, amount, etc.
 */

package com.hexaware.fastx.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking bookingId;

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private double amount;

    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "card|upi|net banking", flags = Pattern.Flag.CASE_INSENSITIVE,
             message = "Payment method must be either 'card', 'upi', or 'net banking'")
    private String paymentMethod;

    @NotBlank(message = "Payment status is required")
    private String paymentStatus;
    private LocalDateTime paymentTime;

    public Payment() {
        super();
    }

    public Payment(int paymentId, Booking bookingId, double amount, String paymentMethod, String paymentStatus,
            LocalDateTime paymentTime) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
    }
    
    public Booking getBookingId() {
        return bookingId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return bookingId;
    }

    public void setBooking(Booking bookingId) {
        this.bookingId = bookingId;
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
                "Booking ID     : " + (bookingId != null ? bookingId.getBookingId() : "N/A") + "\n" +
                "Amount         : " + String.format("%.2f", amount) + "\n" +
                "Payment Method : " + paymentMethod + "\n" +
                "Payment Status : " + paymentStatus + "\n" +
                "Payment Time   : " + paymentTime;
    }
}
