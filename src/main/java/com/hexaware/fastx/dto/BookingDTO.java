package com.hexaware.fastx.dto;

import java.time.LocalDateTime;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class BookingDTO {  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @NotNull(message = "User ID must not be null")
    private int userId;

    @NotNull(message = "Route ID must not be null")
    private int routeId;

    @NotNull(message = "Booking time must not be null")
    private LocalDateTime bookingTime;

    @Min(value = 1, message = "At least one seat must be booked")
    private int seatsBooked;

    @NotNull(message = "Seat numbers must not be null")
    @Size(min = 1, max = 80, message = "Seat numbers must be between 1 and 80 characters")
    private String seatNumbers;

    @Positive(message = "Total amount must be a positive value")
    private double totalAmount;

    @NotNull(message = "Booking status must not be null")
    @Size(min = 3, max = 30, message = "Enter status within 3 to 30 characters")
    private String status;

    public BookingDTO() {
        super();
    }

    public BookingDTO(int bookingId, int userId, int routeId, LocalDateTime bookingTime, int seatsBooked,
                   String seatNumbers, double totalAmount, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.routeId = routeId;
        this.bookingTime = bookingTime;
        this.seatsBooked = seatsBooked;
        this.seatNumbers = seatNumbers;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking Details:\n" +
               "-------------------------\n" +
               "Booking ID   : " + bookingId + "\n" +
               "User ID      : " + userId + "\n" +
               "Route ID     : " + routeId + "\n" +
               "Booking Time : " + bookingTime + "\n" +
               "Seats Booked : " + seatsBooked + "\n" +
               "Seat Numbers : " + seatNumbers + "\n" +
               "Total Amount : " + totalAmount + "\n" +
               "Status       : " + status + "\n";
    }
}
